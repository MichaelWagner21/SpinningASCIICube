class Main {
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    
    String xFiller = "\u001B[37m"+". ";
    String yFiller = "\u001B[37m"+"";

    String xAxis = "\u001B[37m"+"- ";
    String yAxis = "\u001B[37m"+"| ";
    String origin= "\u001B[37m"+"+-";

    String lineFiller[] = {
      "\u001B[31m"+"X ","\u001B[32m"+"O ","\u001B[33m"+"C ",
      "\u001B[34m"+"Q ","\u001B[35m"+"Z ","\u001B[37m"+"U "
    };
    
    int xBound = 31;
    int yBound = 31;
    int zBound = 31;
    
    int xObject = 30;
    int yObject = 15;
    
    double xTrans = 0;
    double yTrans = 0;
    double zTrans = 0;

    double xTrans2 = 0;
    double yTrans2 = 0;
    double zTrans2 = 0;

    double xTrans3 = 0;
    double yTrans3 = 0;
    double zTrans3 = 0;

    double theta = 0;

    double x = 0;
    double y = 0;
    double z = 0;

    double u = 0;

    int eqNo = 6;

    double bx = 0;
    double by = 0;

    int cubeSideLength = 20;
    double cubeRadius = cubeSideLength/2;

    double leftSide = 0;
    double rightSide = 0;

    boolean isCoordDrawn = false;

    double leftEquations[] = new double[eqNo];
    double rightEquations[]= new double[eqNo];

    double leftEquations2[] = new double[eqNo];
    double rightEquations2[]= new double[eqNo];
    
    //Frame Progression
    for (int i=0; i<6000; 
    i++
    ){
   
      //Y Coordinate
      for (int s=0; s<yBound; s++){
        System.out.println(yFiller);

        //X Coordinate
        for (int t=0; t<xBound; t++){
            u = 0;
            isCoordDrawn = false;
          
          
          while (isCoordDrawn == false){
          //Which Equation is being Drawn? Decided by eq below. 
            for (int eq=0; eq<eqNo; eq++){
          //Defines X and Y. These can be used in equations for drawing lines. 
              x = (t)-xBound/2;
              y = (yBound - s)-yBound/2;
              z = u-zBound/2;

              theta = 0.0174533*i;


          //Transforms X, Y, and Z
              xTrans = x;
              yTrans = y*Math.cos(theta)-z*Math.sin(theta);
              zTrans = y*Math.sin(theta)+z*Math.cos(theta);

              xTrans2 = xTrans*Math.cos(2*theta)+zTrans*Math.sin(2*theta);
              yTrans2 = yTrans;
              zTrans2 = -xTrans*Math.sin(2*theta)+zTrans*Math.cos(2*theta);

              xTrans3 = xTrans2*Math.cos(3*theta)-yTrans2*Math.sin(3*theta);
              yTrans3 = xTrans2*Math.sin(3*theta)+yTrans2*Math.cos(3*theta);
              zTrans3 = zTrans2;

          //Below: Defines Equations
              leftEquations[0] = zTrans3;
              leftEquations[1] = yTrans3;
              leftEquations[2] = zTrans3;
              leftEquations[3] = yTrans3;
              leftEquations[4] = xTrans3;
              leftEquations[5] = xTrans3;
              

              rightEquations[0] = -1*cubeRadius;
              rightEquations[1] = cubeRadius;
              rightEquations[2] = cubeRadius;
              rightEquations[3] = -1*cubeRadius;
              rightEquations[4] = -1*cubeRadius;
              rightEquations[5] = cubeRadius;
              
              /*
              leftEquations2[0] = zTrans3;
              leftEquations2[1] = zTrans3;
              leftEquations2[2] = zTrans3;
              leftEquations2[3] = zTrans3;
              leftEquations2[4] = yTrans3;
              leftEquations2[5] = yTrans3;
             

              rightEquations2[0] = -1*cubeRadius;
              rightEquations2[1] = -1*cubeRadius;
              rightEquations2[2] = -1*cubeRadius;
              rightEquations2[3] = -1*cubeRadius;
              rightEquations2[4] = cubeRadius;
              rightEquations2[5] = cubeRadius;
              */

          

          //Below: "Shades in" coordinates where equation is true.
              if ((  (  
        (
        (Math.abs((leftEquations[eq])-(rightEquations[eq]))<1)
    // && (Math.abs((leftEquations2[eq])-(rightEquations2[eq]))<1)
        )

          //Below: Checks to see if right and left sides of equation are real. 
          //The Math.round function rounds to zero if it is not a real number. The below
          //Code Makes sure that the left and right side are real when not rounded. 
                && (  (leftEquations[eq] >=0 || leftEquations[eq]<=0)  && 
                ( rightEquations[eq] >=0 || rightEquations[eq]<=0 )  )  )           
          //Below: Check to make sure two 'X's are not drawn
                && isCoordDrawn==false)
          //Below: Keeps Lines In Bounds
                &&((xTrans3<=cubeRadius)&&(xTrans3>=-1*cubeRadius
                &&(yTrans3<=cubeRadius&&(yTrans3>=-1*cubeRadius
                &&(zTrans3<=cubeRadius&&zTrans3>=-1*cubeRadius))))))
              {
              
                System.out.print(lineFiller[eq]);
                isCoordDrawn = true;
              }
            }
        
            u++;

            if (z>zBound){
              break;
            }
            
          }

          
          
           //Below: Checks if an x is drawn. If it is not, it draws the corresponding "Empty Space"
            if (isCoordDrawn==false){

              if (x==0&&y==0){
              System.out.print(origin);
              }
              
              else if (x==0){
              System.out.print(yAxis);
              }

              else if (y==0){
              System.out.print(xAxis);
              }
              
              else {
              System.out.print(xFiller);
              }
            }
          
        }
      }
    
      //Delay to Display Frame
        try {
          Thread.sleep(100);
        } 
        catch (InterruptedException ex) {
    
          Thread.currentThread().interrupt();
        }

     //Clear Current Frame
      System.out.print("\033[H\033[2J");


      
   
    }

  }
}