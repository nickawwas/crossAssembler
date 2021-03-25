//Intermediate Representation (IR) comprised of parsed LineStatements along with their respective codes
public class InterRep implements IInterRep {
    //Array of line statements
    private final ILineStatement[] lines;
    //Array of addresses for each line statement
    private final int[] addr;

    //Parameterized constructor
    public InterRep(int len) {
        lines = new LineStatement[len];
        addr = new int[len];
    }

    //Add LineStatement with a LineStatement object
    public void addLine(int i, ILineStatement l) {
        lines[i] = l;
    }

    //Add LineStatement with a label, instruction object and comment
    public void addLine(int i, String l, IInstruction in, String c) {
        lines[i] = new LineStatement(l, in, c);
    }

    //Get LineStatement
    public ILineStatement getLine(int i) {
        return lines[i];
    }

    //Get length of LineStatement array
    public int getLength() {
        return lines.length;
    }

    //Set a LineStatement's label
    public void setLabel(int i, String label) {
        lines[i].setLabel(label);
    }

    //Set a LineStatement's comments
    public void setComment(int i, IComment C) {
        lines[i].setComment(C);
    }

    //Check line i for instruction
    public boolean hasInstruction(int i) {
        //Check for empty line or no instruction
        if (lines[i] == null || lines[i].getInstruction().getMnemonic().getMne().equals(""))
            return false;
        return true;
    }

    //Get the directive of a LineStatement
    public IDirective getDirective(int i) { return lines[i].getDirective(); }

    //Check line i for directive
    public boolean hasDirective(int i) {
        //Check for empty line or no directive
        if (lines[i] == null || lines[i].getDirective().getCString().equals(""))
            return false;
        return true;
    }

    //Set a LineStatement's instruction
    public void setInstruction(int i, IInstruction instruction) {
        lines[i].setInstruction(instruction);
    }

    //Get the address of a LineStatement
    public int getAddr(int i) {
        return addr[i];
    }

    //Set the address of a LineStatement
    public void setAddr(int i, int val) {
        addr[i] = val;
    }

    //Returns a string representable of an interRep object
    public String toString() {
        String IR = "";
        for(int i = 0; i < this.getLength(); i++)
            IR = IR.concat(String.format("Line %s: %s", i, lines[i].toString() + "\n"));

        return IR;
    }
}