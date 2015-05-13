import java.io.IOException;
import com.esri.arcgis.addins.desktop.Button;
import com.esri.arcgis.interop.AutomationException;
import javax.swing.JOptionPane;
import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.system.VarArray;

/*@(#) RandomPointCreator.java 1.0 25 May 2012
*
*Copyright (c) School of Geography.
*University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT.
*All rights reserved.
*
*This code is provided under the Academic Academic Free License v. 3.0.
*For details, please see the site http://www.opensource.org/licenses/AFL-3.0.
*/ 

/**
*The RandomPointCreator class is used to create randomised geographical point data that can be used to test the effectiveness of the 'Trafford' Burglary model approach.  
*
*The class utilises the 'Create Random Points' tool held within the Arc toolbox.
*
*@author Nicholas Addis <gy11nja@leeds.ac.uk>
*@version 1.0 25 May 2012
*/
public class RandomPointCreator extends Button {		//This declares the public RandomPointCreator class which extends the Button Class. 

	private GeoProcessor geoprocessor = null;		//This initialises the value of the Geoprocessor variable; 'geoprocessor'. 
	
	/**
	 * This is the public 'onClick' method.
	 * 
	 * This method is called when the button attached to the tool is clicked.
	 * 
	 * @param void no input parameters.
	 * @return no return.
	 * 
	 * @exception java.io.IOException if there are interop problems.
	 * @exception com.esri.arcgis.interop.AutomationException if the component throws an ArcObjects exception.
	 */
	@Override
	public void onClick() throws IOException, AutomationException {		//The public 'onClick' method is implemented upon the user clicking the button attached to this tool. The method also tries to catch any exceptions that may occur.  
		
		try{		//This sets up a 'try-catch' block to catch any exceptions. 
			
			this.geoprocessor = new GeoProcessor();		//This sets up the Geoprocessors, which are then used to implement toolbox tools built into ArcGIS. 
	            
			VarArray parameters = new VarArray();		//This sets up the parameters for the tool. 

			String location = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb";		//These set up values and locations/ resulting directory paths for the specific parameters within the tool, arranged in the same order as within the tool in Arc toolbox. 
			String featureClass = "Leeds_district";		//This specifies the layer which will constrain the location of the random points generated.  
			String pointNumber = "109";		//This sets the number of random points (the same as the number of offences during the current week). 
			String featureClass2 = "RandomPoints";		//This sets the name for the resulting feature layer. This layer is later taken as the input layer for the BurglaryComparativeTool. 
			
			parameters.add(location);		//These add each of the above parameters to the tool add-in.
			parameters.add(featureClass);
			parameters.add(pointNumber);
			parameters.add(featureClass2);
			
			geoprocessor.setOverwriteOutput(true);		//These two lines execute the running of the tool and specify the tool to run. 
			geoprocessor.execute("RandomPointCreator", parameters, null);

			for (int i = 0; i < geoprocessor.getMessageCount(); i++)		//These lines print any messages attached to the tool. 
				JOptionPane.showMessageDialog(null, geoprocessor.getMessage(i));

		} catch (Exception e) {		//This closes the try catch block. 
			JOptionPane.showMessageDialog(null, e.getStackTrace());
			for (int i = 0; i < geoprocessor.getMessageCount(); i++)
				JOptionPane.showMessageDialog(null, geoprocessor.getMessage(i));
		}

	}

}
