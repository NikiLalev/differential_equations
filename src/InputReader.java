import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import VectorModule.CelestialBody;
import VectorModule.StateVector;
import VectorModule.Vector3D;

public class InputReader {
    public static StateVector read(String pathFile) {
        //expected format: number of bodies in first line / one celstial body per line after, x, y, z, vx, vy, vz, mass, name
        int numberOfCelestialBodies = 0;
        CelestialBody[] celestialBodies = new CelestialBody[0];
        int index = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathFile));
            
            String line;

            if((line = br.readLine()) != null) {
                numberOfCelestialBodies = Integer.parseInt(line);
            }
            celestialBodies = new CelestialBody[numberOfCelestialBodies];

            while((line = br.readLine()) != null) {
                String[] celestialBodyArr = line.split(" ");
                Vector3D position = new Vector3D(Double.parseDouble(celestialBodyArr[1]), 
                                                 Double.parseDouble(celestialBodyArr[2]), 
                                                 Double.parseDouble(celestialBodyArr[3]));
                Vector3D velocity = new Vector3D(Double.parseDouble(celestialBodyArr[4]), 
                                                 Double.parseDouble(celestialBodyArr[5]), 
                                                 Double.parseDouble(celestialBodyArr[6]));
                CelestialBody cb = new CelestialBody(position, velocity, 
                                                     Double.parseDouble(celestialBodyArr[7]), 
                                                     celestialBodyArr[0]);
                celestialBodies[index++] = cb;                                 
            }
            br.close();
            
        } catch (FileNotFoundException e1) {
            System.out.println("The file does not exist");
        } catch (IOException e1) {
            System.out.println("Problem reading the file, please try again later");
        }

        //once everything is read
        return new StateVector(celestialBodies);
    }
}



