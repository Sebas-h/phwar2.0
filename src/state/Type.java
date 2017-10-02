package state;

public enum Type {
    ELECTRON(-1), PROTON(1), NEUTRON(0);

    public int value;

    Type(int value){
        this.value = value;
    }
}
