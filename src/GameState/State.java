package GameState;

import java.util.ArrayList;

public class State {

    public ArrayList<Particle> particles = new ArrayList<>();

    public State(ArrayList<Particle> particles) {this.particles = particles;}

    public State(){
        this.particles.add(new Particle(ParticleType.NEUTRON, Colour.BLACK, new GridPosition(0,5,-5)));

        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.BLACK, new GridPosition(-2,5,-3)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.BLACK, new GridPosition(0,4,-4)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.BLACK, new GridPosition(0,3,-3)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.BLACK, new GridPosition(2,3,-5)));

        this.particles.add(new Particle(ParticleType.PROTON, Colour.BLACK, new GridPosition(-1,5,-4)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.BLACK, new GridPosition(-1,4,-3)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.BLACK, new GridPosition(1,4,-5)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.BLACK, new GridPosition(1,3,-4)));

        this.particles.add(new Particle(ParticleType.NEUTRON, Colour.WHITE, new GridPosition(0,-5,5)));

        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.WHITE, new GridPosition(-2,-3,5)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.WHITE, new GridPosition(0,-4,4)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.WHITE, new GridPosition(0,-3,3)));
        this.particles.add(new Particle(ParticleType.ELECTRON, Colour.WHITE, new GridPosition(2,-5,3)));

        this.particles.add(new Particle(ParticleType.PROTON, Colour.WHITE, new GridPosition(-1,-4,5)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.WHITE, new GridPosition(-1,-3,4)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.WHITE, new GridPosition(1,-5,4)));
        this.particles.add(new Particle(ParticleType.PROTON, Colour.WHITE, new GridPosition(1,-4,3)));
    }
}
