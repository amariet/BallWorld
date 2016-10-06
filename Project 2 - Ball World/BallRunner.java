/**
 * Amanda Torres
 * September 01, 2016
 */
public class BallRunner
{
    private static BallWorld ballWorld;
    private static TGPoint entrancePoint;
    private static BallBot[] ballBotArray;
    private static BallRunner ballBotRunner;

    public BallRunner( BallWorld bw, TGPoint entPoint, int ballBotArrayLength) 
    {
        ballWorld = bw;
        entrancePoint = entPoint;
        ballBotArray = new BallBot[ballBotArrayLength];
    }

    public static void run()
    {
        BallWorld ballWorlds = new BallWorld( 200 , 200 );
        TGPoint startPoint = new TGPoint(0 , 0);
        BallRunner ballBotRunner = new BallRunner(ballWorlds, startPoint, 10);
        boolean manzana = true;
        while(manzana)
        {
            int freeBallBotIndex = ballBotRunner.findFreeBallBotIndex();
            if(freeBallBotIndex < ballBotArray.length)
            {
                int r = (int)(Math.random()*50) + 40;
                if(ballBotRunner.entranceClear(r) || freeBallBotIndex == 0)
                {
                    ballBotArray[freeBallBotIndex] = new BallBot(ballWorld, entrancePoint, Math.random() * 360, r);
                    ballBotArray[freeBallBotIndex].setPixelsPerSecond((int)(Math.random()*100) + 100);
                    ballBotArray[freeBallBotIndex].setColor((int)(Math.random()*30));
                }
            }
            
            for(int index = 0; index < ballBotArray.length; index++)
            {
                if(ballBotArray[index] != null)
                {
                    if(ballBotArray[index].canMoveForward(ballWorld) && ballBotRunner.ballBotToBounceOff(ballBotArray[index]) == null)
                    {
                        ballBotArray[index].moveForward();
                    } 
                    else 
                    {
                        if(ballBotRunner.ballBotToBounceOff(ballBotArray[index]) != null)
                        {
                            double trash = ballBotRunner.ballBotToBounceOff(ballBotArray[index].getHeading() - (Math.random()*10 + 200))
                            double potato = ballBotArray[index].getHeading
                            // continue to try and fix bugs & complete activity 5
                        }
                        ballBotArray[index].setHeading(Math.random()*360);
                        ballBotArray[index].moveForward();
                    }
                }
            }
        }
    }

    public int findFreeBallBotIndex() 
    { 
        for(int i = 0; i < ballBotArray.length; i++)
        {
            if(ballBotArray[i] == null)
            {
                return i;
            }
        }
        return ballBotArray.length;
    }

    public double distanceBetweenPoints(TGPoint point1, TGPoint point2)
    {
        double a = (point1.x - point2.x) * (point1.x - point2.x);
        double b = (point1.y - point2.y) * (point1.y - point2.y);
        return Math.sqrt(a + b);
    }

    public boolean entranceClear(int myRadius)
    {
        for(int i = 0; i < ballBotArray.length; i++)
        {
            if(ballBotArray[i] != null)
            {
                if(ballBotRunner.distanceBetweenPoints(entrancePoint, ballBotArray[i].getPoint()) < (myRadius * ballBotRunner[i].getRadius()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public BallBot ballBotToBounceOff(BallBot ballBot)
    {
        TGPoint firstPoint = ballBot.getPoint();
        TGPoint nextPoint = ballBot.forwardPoint();
        for(int i = 0; i < ballBotArray.length; i++)
        {
            if(ballBotArray[i] != null)
            {
                if(ballBotArray[i] != ballBot)
                {
                    double currentDistance = distanceBetweenPoints(firstPoint, ballBotArray[i].getPoint());
                    int sumOfRadii = ballBot.getRadius() + ballBotArray[i].getRadius();
                    if(currentDistance <= sumOfRadii)
                    {
                        double nextDistance = distanceBetweenPoints(nextPoint, ballBotArray[i].getPoint());
                        if(nextDistance <= currentDistance)
                        {
                            return ballBotArray[i];
                        }
                        return ballBotArray[i];
                    }
                }
            }
        }
        return null;
    }

    public static void activity1()
    {
        BallWorld ballWorld = new BallWorld( 200 , 200 );
        TGPoint startPoint = new TGPoint( 0 , 0 );
        BallBot ballBot = new BallBot(ballWorld, startPoint, 90 , 60 );
        while(true) 
        {
            if (ballBot.canMoveForward(ballWorld)){
                ballBot.moveForward();
            }
            else {
                double ball1 = ballBot.getHeading() + 120;
                if(ball1 > 360) {
                    ball1 = ball1%360;
                }
                ballBot.setHeading(ball1);
                System.out.println(ball1);
            }
        } 
    }
}
