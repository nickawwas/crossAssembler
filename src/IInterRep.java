//Interface for InterRep class
public interface IInterRep {

    //Set LineStatement with a LineStatement object
    void setLine(int i, ILineStatement l);

    //Set LineStatement with a label, Instruction object and comment
    void setLine(int i, String l, IInstruction in, String c);

    //Set LineStatement with an Instruction object and comment
    void setLine(int i, IInstruction in, String c);

    //Set LineStatement with a label and Instruction object
    void setLine(int i, String l, IInstruction in);

    //Set LineStatement with a label
    void setLine(int i, String l);

    //Set LineStatement with a label and comment
    void setLine(int i, String l, String c);

    //Set the code of a particular LineStatement
    void setCode(int i, int code);

    //Get LineStatement
    ILineStatement getLine(int i);

    //Returns the code of the mnemonic
    public int getCode(int i);

    //Get length of LineStatements array
    int getLength();

    //Returns a String representable of an InterRep object
    String toString();
}
