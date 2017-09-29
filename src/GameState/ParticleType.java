package GameState;

public enum ParticleType {
    ELECTRON(-1), PROTON(1), NEUTRON(0);

    private int value;

    private ParticleType(int value){
        this.value = value;
    }
}
