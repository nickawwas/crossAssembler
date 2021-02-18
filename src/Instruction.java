public class Instruction {
    private String mnemonic = null;
    public String operand = null;

    //Default Constructor
    public Instruction() {}

    //Parameterized Constructor
    public Instruction(String m, String o) {
        mnemonic = m;
        operand = o;
    }

    //Parameterized Constructor
    public Instruction(String m) {
        mnemonic = m;
    }

    //Set Mnemonic + Operand
    public void setMnemonic(String m) {
        mnemonic = m;
    }

    public void setOperand(String o) {
        operand = o;
    }

    //Get Mnemonic + Operand
    public String getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }

    // TODO: find a workaround without having to create an instructionset object
    public Integer getCode() {
        SymbolTable set = new SymbolTable();
        return set.getCode(mnemonic);
    }

    //Check If Operand is Digit (True) or Label (False)
    public boolean isDigit() {
        return operand.matches("[0-9]+");
    }
}