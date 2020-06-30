public class NBody {


    public static double readRadius(String s){
        In in = new In(s);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static double readPlanet(String s){
        In in = new In(s);
        int n = in.readInt();
        in.readDouble();
        Planet[] allPlanets = new Planet[n];
        for(int i=0; i<n; i++){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            allPlanets[i]=new Planet(xP,yP,xV,yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args){
        //args is a String array save all commands line input.
        double T= Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);
        String fileName= args[2];
        if(args.length>3){
            NBody.backgroundImage = args[3];
        }

        //get radius for fileName input in args[2]
        double radius =readRadius(fileName);
        Planet[] allPlanets = readPlanet(fileName);
        //scale the universe to r
        StdDraw.setScale(-radius, radius);
        //clear drawing window
        StdDraw.clear();

        //play the space-audio
        StdAudio.play(backgroundMusic);

        double t=0.0; //time variable
        int n=allPlanets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        while(t<T){
            for(int i=0; i<n; i++){
                double fX=allPlanets[i].calcNetForceExertedByX(allPlanets);
                double fY=allPlanets[i].calcNetForceExertedByY(allPlanets);
                xForces[i]=fX;
                yForces[i]=fY;
            }
            /** Update each Planet's members */
            for (int i = 0; i < n; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Show the background */
            StdDraw.picture(0, 0, NBody.backgroundImage);

            /** Draw all of the Planets */
            for (Planet p : allPlanets) {
                p.draw();
            }

            /* Shows the drawing to the screen */
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        /**
         Outputs the final states of each Planet for autograder to work correctly
         */
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (Planet p : allPlanets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }

}