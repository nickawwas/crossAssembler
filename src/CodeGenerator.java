import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

//Generates executable file and listing file
public class CodeGenerator implements ICodeGenerator {

    //Instance of the IR
    private IInterRep interRep;
    //Array of machine codes for each LineStatement
    private String[] mCode;

    //Default constructor
    public CodeGenerator(IInterRep IR, IOptions options) {

        interRep = IR;
        mCode = new String[interRep.getLength()];

        generateMachineCode();

        //Generate listing file with label table
        //Options not yet in use
        if (options.isVerbose()){
            //TODO: Need to implement extra functionality for verbose option
            IListing listing = new Listing(IR, mCode);
            String [] lstContent = listing.getListing();
            generateListing(lstContent);

            //Print label table also
        }
        //Generate listing file
        else if (options.isListing()){
            IListing listing = new Listing(IR, mCode);
            String [] lstContent = listing.getListing();
            generateListing(lstContent);
        }

        //Return Bin
    }

    //Generate a listing file
    public void generateListing(String[] lstContent) {
        // Create listing.lst output file
        try {
            FileOutputStream fs = new FileOutputStream(new File("listing.lst"));

            // Write to listing.lst file
            for(String l : lstContent) {
                char[] cArr = l.toCharArray();
                for(char c : cArr)
                    fs.write(c);
            }

            //Add EOF
            fs.write('\n');

            // Close listing.lst file
            fs.close();

        } catch (Exception e) { e.printStackTrace(); }
    }

    //Sets the machine code of each line statement
    public void generateMachineCode() {
        //Set the machine code of each line statement
        for (int i = 0; i < interRep.getLength(); i++) {
            //Get the opcode and operand of the line statement
            int opcode = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();
            String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
            //If operand is a label or string
            if (!interRep.getLine(i).getInstruction().getOperand().isNumeric() && operand != "") {
                //If a line's mnemonic is a .cstring, set its machine code to its opcode + the byte size of each character in the string operand
                if (opcode == 0x41) {
                    String op = operand.substring(1, operand.length() - 1);
                    char[] arr = op.toCharArray();
                    String code = "";
                    //Append hex bytes to machine code
                    for (char c : arr) {
                        code = code + " " + Integer.toHexString(c).toUpperCase();
                    }
                    //Append '00' as remaining bytes to machine code
                    for (int j = interRep.getLine(i).getInstruction().getSize() - arr.length; j > 0; j--) {
                        code = code + " 00";
                    }

                    code = code.substring(1);
                    mCode[i] = code;
                }
                //If operand is a label, set the machine code to the instruction's opcode + the
                else {
                    String label = interRep.getLine(i).getInstruction().getOperand().getOp();
                    int code = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();
                    //Fine the address where the label is declared
                    for (int j = i + 1; j < interRep.getLength(); j++) {
                        String currLabel = interRep.getLine(j).getLabel();
                        if (currLabel.equals(label)) {
                            int address = interRep.getAddr(j);
                            mCode[i] = String.format("%s %s", Integer.toHexString(code).toUpperCase(), String.format("%1$04X", address));
                        } else {
                            //TODO: Throw error here
                        }
                    }
                }
            } else if (interRep.getLine(i).getInstruction().getOperand().isNumeric()) {
                mCode[i] = Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode()).toUpperCase();
            }
        }
    }

    //Generate an executable file
    public void generateExec(String fn, String c) {
        try {
            String fileName = fn;
            String content = c;

            //Create output stream and empty file
            BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(new File(fileName + ".bin")));

            //Write to file
            byte[] contentB = content.getBytes();
            for(byte b: contentB)
                bfos.write(b);

            bfos.close();
        } catch (Exception e) { e.getMessage(); }
    }
}