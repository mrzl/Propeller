package main;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by mar on 05.07.15.
 */
public class Propeller {

    private PropellerDisplay parent;
    private PVector center;


    private float startLength;
    private float currentLength;
    private boolean direction;
    private float stepSize;
    private int stepCount;
    private float startStep;
    private float currentStep;
    private float spikyness;
    private float stepIncrease;
    private int color;
    private float lineThickness;

    int turnCounter = 0;
    int turnChangeIndex = 1;

    public int radiusUpdateType = 0;

    public Propeller( PropellerDisplay parent, PVector centerPos, float startLength, boolean rotationDirection, int steps, int currentStep, float stepIncrease, float spikyness, int color, float lineThickness ) {
        this.parent = parent;
        this.center = centerPos;
        this.direction = rotationDirection;
        this.startLength = startLength;
        this.currentLength = startLength;
        this.stepCount = steps;
        this.stepSize = ( float ) (Math.PI * 2 / ( float )( steps ));
        this.stepIncrease = stepIncrease;
        this.startStep = currentStep;
        this.currentStep = currentStep;
        this.spikyness = spikyness;
        this.color = color;
        this.lineThickness = lineThickness;
    }

    public void update() {
        if( this.currentStep == this.startStep ) {
            //this.parent.toggleColorChange();
            this.turnCounter++;
        }

        if( this.direction ) {
            this.currentStep += this.stepIncrease;
            this.currentStep %= this.stepCount;
        } else {
            this.currentStep -= this.stepIncrease;
            if( this.currentStep < 0 ) {
                this.currentStep = this.stepCount;
            }
        }

        switch( this.radiusUpdateType ) {
            case 0:
                this.currentLength += this.parent.random( -this.spikyness, this.spikyness );
                break;
            case 1:
                this.currentLength = this.parent.random( this.startLength );
                break;
        }
    }

    public void draw() {
        this.parent.strokeWeight( this.lineThickness );
        this.parent.stroke( this.color );
        float _x = ( float ) (this.currentLength * Math.sin( this.currentStep * this.stepSize ) + this.center.x);
        float _y = ( float ) (this.currentLength * Math.cos( this.currentStep * this.stepSize ) + this.center.y);
        this.parent.line( this.center.x, this.center.y, _x, _y );
    }

    public void setColor( int _color ) {
        this.color = _color;
    }

    public float getStartLength () {
        return startLength;
    }

    public void setStartLength ( float startLength ) {
        this.startLength = startLength;
    }

    public PVector getCenter () {
        return center;
    }

    public void setCenter ( PVector center ) {
        this.center = center;
    }

    public float getCurrentLength () {
        return currentLength;
    }

    public void setCurrentLength ( float currentLength ) {
        this.currentLength = currentLength;
    }

    public boolean isDirection () {
        return direction;
    }

    public void setDirection ( boolean direction ) {
        this.direction = direction;
    }

    public float getStepSize () {
        return stepSize;
    }

    public void setStepSize ( float stepSize ) {
        this.stepSize = stepSize;
    }

    public int getStepCount () {
        return stepCount;
    }

    public void setStepCount ( int stepCount ) {
        this.stepCount = stepCount;
    }

    public float getStartStep () {
        return startStep;
    }

    public void setStartStep ( float startStep ) {
        this.startStep = startStep;
    }

    public float getCurrentStep () {
        return currentStep;
    }

    public void setCurrentStep ( float currentStep ) {
        this.currentStep = currentStep;
    }

    public float getSpikyness () {
        return spikyness;
    }

    public void setSpikyness ( float spikyness ) {
        this.spikyness = spikyness;
    }

    public float getStepIncrease () {
        return stepIncrease;
    }

    public void setStepIncrease ( float stepIncrease ) {
        this.stepIncrease = stepIncrease;
    }

    public float getLineThickness () {
        return lineThickness;
    }

    public void setLineThickness ( float lineThickness ) {
        this.lineThickness = lineThickness;
    }
}
