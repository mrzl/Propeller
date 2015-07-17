package main;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by mar on 05.07.15.
 *
 * Created for the 'LIVE' exhibition at chelsea space (http://www.chelseaspace.org/)
 * that will run from 4th to the 10th September 2015
 */
public class Propeller extends PApplet {

    private ArrayList< SinglePropeller > propellers;
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
        boolean colorSelect = random( 1 ) > 0.5f ? true : false;

        this.propellers = new ArrayList< SinglePropeller >();
        for( int i = 0; i < propellerCount; i++ ) {
            Propeller parent = this;
            PVector center = new PVector( random( -100, width + 100 ), random( -100, height + 100 ) );
            float startLength = 150;
            boolean direction = random( 1 ) > 0.5 ? true : false;
            int steps = 1000;
            int currentStep = ( int ) random( steps );
            float speed = 2;
            float spikiness = random( 1 );
            int color = 0;
            if( colorSelect ) {
                color = this.color( random( 90 ), random( 30 ) );
            } else {
                color = this.color( random( 255 ), random( 100 ), random( 100 ), random( 30 ) );
            }
            float lineThickness = 1f;
            SinglePropeller p = new SinglePropeller( parent, center, startLength, direction, steps, currentStep, speed, spikiness, color, lineThickness );
            this.propellers.add ( p );
        }

        background( 255 );
    }

    public void draw() {
        for( SinglePropeller p : this.propellers ) {
            p.update();
            p.draw();
        }
        if( this.propellers.get( 0 ).getCurrentStep() == this.propellers.get( 0 ).getStartStep() && this.propellers.get( 0 ).turnCounter == 1 ) {
            toggleColorChange( );
            this.propellers.get( 0 ).turnCounter = 0;
        }
    }

    public void toggleColorChange () {
        this.backgroundColor = !this.backgroundColor;

        if ( this.backgroundColor ) {
            this.background( 0 );
        } else {
            this.background( 255 );
        }

        int radiusUpdateType = ( int ) random( 2 );

        boolean colorSelect = random( 1 ) > 0.5f ? true : false;

        for( SinglePropeller p : this.propellers ) {
            int color = 0;
            if( colorSelect ) {
                color = this.color( random( 90 ), random( 30 ) );
            } else {
                color = this.color( random( 255 ), random( 100 ), random( 100 ), random( 30 ) );
            }
            p.setColor( color );
            p.radiusUpdateType = radiusUpdateType;
            p.setStartLength( 100 );
            p.setCurrentLength( 100 );
        }
    }
}
