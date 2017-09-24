package GameState;

import java.util.ArrayList;

public class State {

    public ArrayList<Particle> blackParticles = new ArrayList<>();

    public ArrayList<Particle> whiteParticles = new ArrayList<>();

    public State(){
        this.blackParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.BLACK, new GridPosition(0,5,-5)));

        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(-2,5,-3)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,4,-4)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,3,-3)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(2,3,-5)));

        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,5,-4)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,4,-3)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,4,-5)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,3,-4)));

        this.whiteParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.WHITE, new GridPosition(0,-5,5)));

        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(-2,-3,5)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-4,4)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-3,3)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(2,-5,3)));

        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-4,5)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-3,4)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-5,4)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-4,3)));
    }
}
