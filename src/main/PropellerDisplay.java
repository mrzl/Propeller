package main;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by mar on 05.07.15.
 */
public class PropellerDisplay extends PApplet {

    private ArrayList< Propeller > propellers;
    boolean backgroundColor = false;


    public void init() {
        frame.removeNotify( );
        frame.setUndecorated( true );
        frame.addNotify();
        super.init( );
    }
    public void setup() {
        size( 1920, 1080, P3D );
        smooth( );
        noCursor( );

        int propellerCount = 1200;

        this.propellers = new ArrayList< Propeller >();
        for( int i = 0; i < propellerCount; i++ ) {
            PropellerDisplay parent = this;
            PVector center = new PVector( random( -100, width + 100 ), random( -100, height + 100 ) );
            float startLength = 150;
            boolean direction = random( 1 ) > 0.5 ? true : false;
            int steps = 1000;
            int currentStep = ( int ) random( steps );
            float speed = 2;//random( 1, 5 );
            float spikyness = random( 1 );
            int color = this.color( random( 200 ), random( 100 ), random( 100 ), random( 30 ) );
            float lineThickness = 1f;
            Propeller p = new Propeller( parent, center, startLength, direction, steps, currentStep, speed, spikyness, color, lineThickness );
            this.propellers.add ( p );
        }

        background( 255 );
    }

    public void draw() {
        for( Propeller p : this.propellers ) {
            p.update();
            p.draw();
        }
        if( this.propellers.get( 0 ).getCurrentStep() == this.propellers.get( 0 ).getStartStep() ) {
            toggleColorChange( );
        }
    }

    public void toggleColorChange () {
        System.out.println( "toggled " + System.currentTimeMillis() );
        this.backgroundColor = !this.backgroundColor;

        if ( this.backgroundColor ) {
            this.background( 0 );
        } else {
            this.background( 255 );
        }

        int radiusUpdateType = ( int ) random( 2 );
        for( Propeller p : this.propellers ) {
            int color = this.color( random( 255 ), random( 100 ), random( 100 ), random( 30 ) );
            p.setColor( color );
            p.radiusUpdateType = radiusUpdateType;
            p.setStartLength( 100 );
            p.setCurrentLength( 100 );
        }
    }
}
