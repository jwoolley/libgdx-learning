package com.mygdx.graphics;

public class RGBColor{
  public RGBColor() {
    this(0.0f,0.0f,0.0f);
  }

  public RGBColor(float r, float g, float b) {
    this(r, g, b,1.0f);
  }

  public RGBColor(float r, float g, float b, float a) {
    this.colorR = r;
    this.colorG = g;
    this.colorB = b;
    this.colorA = a;
  }

  public float colorR;
  public float colorG ;
  public float colorB;
  public float colorA;
}
