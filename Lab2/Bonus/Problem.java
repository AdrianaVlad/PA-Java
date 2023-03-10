
package com.mycompany.bonus;
import java.util.*;
/**
* Problem implements an instance of the following problem:
* Given a list of locations and roads, determine the best route
* from one location to another.
* <p>
* Such an instance includes:
* <ul>
* <li> a list of locations
* <li> a list of roads
* <li> a beginning location
* <li> an end location
* <li> a private map to easily access the index of a location in the list
* </ul>
* <p>
* The empty constructor initializes those values with a default example.
* Other classes can also:
* <ul>
* <li> initialize the object with different values / later change the values
* <li> manually check the validity of a future initialization
* <li> see if there is a path (of 0 or more roads) the set start and finish locations
* <li> see the shortest path between the set start and finish locations
* <li> see the fastest path between the set start and finish locations
* </ul>
* <p>
* The suggested paths do not take gas consumption into consideration.
* 
* @author Vlad Adriana
*/
public class Problem {
    protected Location[] locationList;
    protected Road[] roadList;
    protected Location start;
    protected Location finish;
    private Map<Location,Integer> locationIndex = new HashMap<>();
    Road[][] roadAdjacency;
    /**
    * Manual initialization of an instance of the problem. It is also
    * the only way to change the values of an instance that has already been
    * initialized.
    * <p>
    * It also calls checkValidity and does not allow the initialization
    * of an invalid instance. In case the values are invalid, the values will
    * remain unchanged.
    * 
    * @param locationList  a list of all locations
    * @param roadList      a list of all roads
    * @param start         the starting location
    * @param finish        the destination
    */
    public Problem(Location[] locationList, Road[] roadList, Location start, Location finish) {
        if(checkValidity(locationList, roadList, start, finish)==false){
            System.out.println("Invalid instance of problem! Values not changed");
            return;
        }
        this.locationList = locationList;
        this.roadList = roadList;
        this.start = start;
        this.finish = finish;
        int i;
        for(i=0;i<locationList.length;i++)
            locationIndex.put(locationList[i], i);
        roadAdjacency = new Road[locationList.length][locationList.length];
        for(i=0;i<roadList.length;i++){
            roadAdjacency[locationIndex.get(roadList[i].end1)][locationIndex.get(roadList[i].end2)] = roadList[i];
            roadAdjacency[locationIndex.get(roadList[i].end2)][locationIndex.get(roadList[i].end1)] = roadList[i];
        }
    }
    /**
    * The default initial values, should no values be manually added. It also
    * serves as an example of a valid instance.
    */
    public Problem(){
        Location iasi= new City(800_000,"city",10,20,"Iasi");
        Location vaslui = new City(400_000,"city",10,30,"Vaslui");
        Road r1 = new Road(Road.RoadType.COUNTRY,15,50,iasi,vaslui);
        Location iasiAirport = new Airport(2,"airport",11,23,"Aeroportul Iasi");
        Road r2 = new Road(Road.RoadType.EXPRESS,5,60,iasi,iasiAirport);
        Location bucuresti = new City(1_800_000,"city",5,60,"Bucuresti");
        Road r3 = new Road(Road.RoadType.COUNTRY,50,50,bucuresti,iasiAirport);
        Location omv1 = new GasStation(6.5f,"gas station",5,55,"OMV");
        Location omv2 =new GasStation(6.7f,"gas station",5,45,"OMV");
        Road r4 = new Road(Road.RoadType.EXPRESS,13,60,omv1,omv2);
        Location constanta = new City(300_000,"city",13,60,"Constanta");
        Road r5 = new Road(Road.RoadType.HIGHWAY,10,100,bucuresti,constanta);
        locationList = new Location[]{iasi,vaslui,iasiAirport,bucuresti,omv1,omv2,constanta};
        roadList = new Road[]{r1,r2,r3,r4,r5};
        start = locationList[0];
        finish = locationList[6];
        int i;
        for(i=0;i<locationList.length;i++)
            locationIndex.put(locationList[i], i);
        roadAdjacency = new Road[locationList.length][locationList.length];
        for(i=0;i<roadList.length;i++){
            roadAdjacency[locationIndex.get(roadList[i].end1)][locationIndex.get(roadList[i].end2)] = roadList[i];
            roadAdjacency[locationIndex.get(roadList[i].end2)][locationIndex.get(roadList[i].end1)] = roadList[i];
        }
    }
    /**
    * Checks the validity of an possible instance, either before initialization
    * or when manually called in a different class.
    * 
    * @param locationList  a list of all locations
    * @param roadList      a list of all roads
    * @param start         the starting location
    * @param finish        the destination
    * @return              <code> false </code> if start and finish locations
    *                      are not found in the location list, if a road is
    *                      shorter than the Euclidian distance between its ends
    *                      , if a road is from/to a location not in the list or
    *                      if a location or road is present multiple times.
    *                      <code> true </code> otherwise.
    */
    public final boolean checkValidity(Location[] locationList, Road[] roadList, Location start, Location finish){
        int i, j, includes=0;
        for(i=0;i<locationList.length;i++){
            if(locationList[i]==start)
                includes++;
            else if(locationList[i]==finish)
                includes++;
            for(j=i+1;j<locationList.length;j++)
                if(locationList[i]==locationList[j])
                    return false;
        }
        if(includes!=2)
            return false;
        for(i=0;i<roadList.length;i++){
            if(locationIndex.get(roadList[i].end1)==null)
                return false;
            if(locationIndex.get(roadList[i].end2)==null)
                return false;
            if(distanceBetween(roadList[i].end1,roadList[i].end2)>(float)roadList[i].length){
                return false;
            }
            for(j=i+1;j<roadList.length;j++)
                if(roadList[i]==roadList[j])
                    return false;
        }
        return true;
    }
    /**
    * Computes the Euclidian distance between any 2 given locations as a 
    * positive float.
    * 
    * @param l1            a list of all locations
    * @param l2            a list of all roads
    * @return              the Euclidian distance between l1 and l2
    */
    private float distanceBetween(Location l1, Location l2){
        float xDistance = Math.abs(l1.xCoord-l2.xCoord);
        float yDistance = Math.abs(l1.yCoord-l2.yCoord);
        return (float)Math.sqrt(xDistance*xDistance+yDistance*yDistance);
    }
    /**
    * This method solves a more simple variation of the problem. It checks 
    * weather or not there exists a path between the start and finish locations
    * of the instance. The path can be made of 0 roads, in case start = finish,
    * or it can consist of 1 or multiple interconnected roads. 
    * <p>
    * Two roads are interconnected if they have at least one end in common.
    * <p>
    * For this method, the distance or duration it would take to traverse the
    * path does not matter. It only checks if such a path exists, not how good
    * it is.
    * @return                <code> true </code> if there is a path between the
    *                        start and finish locations, <code> false </code>
    *                        otherwise.
    */
    public boolean existsPath(){
        int i;
        boolean visited[]=new boolean[locationList.length];
        Location l;
        Stack <Location> stack = new Stack<>();
        stack.push(start);
        while(!stack.empty()){
            l=stack.pop();
            visited[locationIndex.get(l)]=true;
            if(l==finish)
                return true;
            for(i=0;i<roadList.length;i++){
                if(roadList[i].end1==l && visited[locationIndex.get(roadList[i].end2)]==false){
                    stack.push(roadList[i].end2);
                }
                else if(roadList[i].end2==l && visited[locationIndex.get(roadList[i].end1)]==false){
                    stack.push(roadList[i].end1);
                }
            }
        }
        return false;
    }
    public Solution findShortestPath(){
        float pathLength[] = new float[locationList.length];
        int i;
        int before[] = new int[locationList.length];
        for(i=0;i<pathLength.length;i++){
            if(roadAdjacency[locationIndex.get(start)][i]!=null)
                pathLength[i]=roadAdjacency[locationIndex.get(start)][i].length;
            else
                pathLength[i]=(float)1e9;
            before[i]=locationIndex.get(start);
        }
        boolean visited[]=new boolean[locationList.length];
        visited[locationIndex.get(start)]=true;
        int minLengthIndex = locationIndex.get(start);
        float minLength;
        while(locationList[minLengthIndex]!=finish){
            minLength=(int)1e9;
            for(i=0;i<pathLength.length;i++){
                if(visited[i]==false && minLength > pathLength[i]){
                    minLength=pathLength[i];
                    minLengthIndex=i;
                }
            }
            visited[minLengthIndex]=true;
            for(i=0;i<locationList.length;i++){
                if(visited[i]==false && roadAdjacency[minLengthIndex][i]!=null && pathLength[i] > minLength + roadAdjacency[minLengthIndex][i].length){
                    pathLength[i] = minLength + roadAdjacency[minLengthIndex][i].length;
                    before[i] = minLengthIndex;
                }
            }
        }
        int nrOfRoads=0;
        for(i=locationIndex.get(finish);i!=locationIndex.get(start);i=before[i])
            nrOfRoads++;
        Road[] solutionPath = new Road[nrOfRoads];
        for(i=locationIndex.get(finish), nrOfRoads=0;i!=locationIndex.get(start);i=before[i],nrOfRoads++)
            solutionPath[solutionPath.length-nrOfRoads-1]=roadAdjacency[before[i]][i];
        return new Solution(solutionPath,pathLength[locationIndex.get(finish)],0);
    }
    public Solution findFastestPath(){
        float pathDuration[] = new float[locationList.length];
        int i;
        int before[] = new int[locationList.length];
        for(i=0;i<pathDuration.length;i++){
            if(roadAdjacency[locationIndex.get(start)][i]!=null)
                pathDuration[i]=roadAdjacency[locationIndex.get(start)][i].length/roadAdjacency[locationIndex.get(start)][i].speedLimit;
            else
                pathDuration[i]=(float)1e9;
            before[i]=locationIndex.get(start);
        }
        boolean visited[]=new boolean[locationList.length];
        visited[locationIndex.get(start)]=true;
        int minDurationIndex = locationIndex.get(start);
        float minDuration;
        while(locationList[minDurationIndex]!=finish){
            minDuration=(int)1e9;
            for(i=0;i<pathDuration.length;i++){
                if(visited[i]==false && minDuration > pathDuration[i]){
                    minDuration=pathDuration[i];
                    minDurationIndex=i;
                }
            }
            visited[minDurationIndex]=true;
            for(i=0;i<locationList.length;i++){
                if(visited[i]==false && roadAdjacency[minDurationIndex][i]!=null && pathDuration[i] > minDuration + roadAdjacency[minDurationIndex][i].length / roadAdjacency[minDurationIndex][i].speedLimit){
                    pathDuration[i] = minDuration + roadAdjacency[minDurationIndex][i].length / roadAdjacency[minDurationIndex][i].speedLimit;
                    before[i] = minDurationIndex;
                }
            }
        }
        int nrOfRoads=0;
        for(i=locationIndex.get(finish);i!=locationIndex.get(start);i=before[i])
            nrOfRoads++;
        Road[] solutionPath = new Road[nrOfRoads];
        for(i=locationIndex.get(finish), nrOfRoads=0;i!=locationIndex.get(start);i=before[i],nrOfRoads++)
            solutionPath[solutionPath.length-nrOfRoads-1]=roadAdjacency[before[i]][i];
        return new Solution(solutionPath,0,pathDuration[locationIndex.get(finish)]);
    }
}
