public class Planet {

    private static final  double G=6.67*1e-11;
    public double xxPos; // current x position
    public double yyPos; // current y position
    public double xxVel; // current velocity in the x direction
    public double yyVel; // current velocity in the y direction
    public double mass; // mass
    public String imgFileName; // name of the file that corresponds to the image that depicts the planet, e.g. jupiter.gif

    //Constructor to initialize an instance of the Planet class with given parameters
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    //Constructor to copy given Planet object
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double diffX=xxPos - p.xxPos;
        double diffY= yyPos - p.yyPos;
        double distance = Math.sqrt(diffX*diffX+diffY*diffY);
        return distance;
    }

    public  double calcForceExertedBy(Planet p){
        double r=calcDistance(p);
        double F= G* mass * p.mass/(r*r);
        return  F;
    }

    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffX = p.xxPos - xxPos;
        double fX = F * diffX / r;
        return fX;
    }

    /**
     Calculates the force exerted in the Y direction
     */
    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffY = p.yyPos - yyPos;
        double fY = F * diffY / r;
        return fY;
    }

    public Boolean equals(Planet p){
        if(xxPos==p.xxPos && yyPos== p.yyPos &&xxVel == p.xxVel &&
                yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName){
            return  true;
        } else {
            return false;
        }
    }

    public double calcNetForceExertedByX( Planet[] allPlanets){
        double fxNet = 0.0;
        for(Planet p: allPlanets){
            if(equals(p)){
                continue;
            } else {
                fxNet += calcForceExertedByX(p);
            }
        }
        return fxNet;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double fYNet = 0.0;
        for (Planet p: allPlanets) {
            if (equals(p)) {
                continue;
            } else {
                fYNet += calcForceExertedByY(p);
            }
        }
        return fYNet;
    }

    public void update(double dt, double fx, double fy){
        double aXNet =fx/mass;
        double aYNet =fy/mass;

        xxVel+= dt*aXNet;
        yyVel+= dt*aYNet;
        xxPos+= dt* xxVel;
        yyPos+= dt* yyVel;
    }








}