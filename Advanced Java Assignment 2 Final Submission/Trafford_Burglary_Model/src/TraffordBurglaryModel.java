
import java.io.IOException;
import com.esri.arcgis.addins.desktop.Button;
import com.esri.arcgis.interop.AutomationException;
import javax.swing.JOptionPane;
import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.system.VarArray;

/*@(#) TraffordBurglaryModel.java 1.0 25 May 2012
*
*Copyright (c) School of Geography.
*University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT.
*All rights reserved.
*
*This code is provided under the Academic Academic Free License v. 3.0.
*For details, please see the site http://www.opensource.org/licenses/AFL-3.0.
*/ 

/**
*The TraffordBurglaryModel class implements the methods within the 'TraffordBurglaryModel' tool, used to help detect crime patterns and predict future crime occurrences. 
*
*The class implements a series of buffers around previous offences; and subsequently explores the extent to which these intersect offences that occurred during the current week, 
*and therefore the extent to which these may 'predict' offences. 
*
*@author Nicholas Addis <gy11nja@leeds.ac.uk>
*@version 1.0 25 May 2012
*/
public class TraffordBurglaryModel extends Button {		//This declares the public TraffordBurglaryModel class which extends the Button class. 

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

			String inputFeatures = "LAST_WEEK_WK_48_DATA";		//These set up values and locations/ resulting directory paths for the specific parameters within the tool, arranged in the same order as within the tool in Arc toolbox. This line is for last week's offences.
			String distance = "400";		//Buffer distance (in metres).
			String featureClass = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_last_weeks_buffer";		//Resulting file path and layer name. 
			String inputFeatures2 = "TWO_WEEKS_AGO_WK_47_DATA";		//Offences from two weeks ago.
			String featureClass3 = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_two_weeks_ago_buffer";		//Resulting file path and layer name.
			String inputFeatures3 = "THREE_WEEKS_AGO_WK_46_DATA";		//Offences from three weeks ago.
			String featureClass5 = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_three_weeks_ago_buffer";		//Resulting file path and layer name.
			String featureLayer = "THIS_WEEK_WK_49_DATA";		//The current week's offences; to undertake predictive analysis on. 
			String featureClass2 = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_high_risk_predicted";		//Resulting file path and layer name for current offences predicted from offences in the last week.
			String featureClass4 = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_high_medium_risk_predicted";		//Resulting file path and layer name for current offences predicted from offences in the last two weeks.
			String featureClass6 = "C:\\Users\\Nick\\Documents\\ArcGIS\\Default.gdb\\Export_Output_all_risk_predicted";		//Resulting file path and layer name for current offences predicted from offences in the last three weeks.
			String featureClass7 = "C:\\Users\\Nick\\Documents\\MSc GIS Leeds\\Advanced Java Assignment 2 Final Submission";		//Resulting file paths for the dbf files saving details of predicted offences.
			
			parameters.add(inputFeatures);		//These add each of the above parameters to the tool add-in.
			parameters.add(distance);
			parameters.add(featureClass);
			parameters.add(inputFeatures2);
			parameters.add(featureClass3);
			parameters.add(inputFeatures3);
			parameters.add(featureClass5);
			parameters.add(featureLayer);
			parameters.add(featureClass2);
			parameters.add(featureClass4);
			parameters.add(featureClass6);
			parameters.add(featureClass7);
			
			geoprocessor.setOverwriteOutput(true);		//These two lines execute the running of the tool and specify the tool to run.
			geoprocessor.execute("TraffordBurglaryModel", parameters, null);

			for (int i = 0; i < geoprocessor.getMessageCount(); i++)		//These lines print any messages attached to the tool. 
				JOptionPane.showMessageDialog(null, geoprocessor.getMessage(i));

		} catch (Exception e) {		//This closes the try catch block. 
			JOptionPane.showMessageDialog(null, e.getStackTrace());
			for (int i = 0; i < geoprocessor.getMessageCount(); i++)
				JOptionPane.showMessageDialog(null, geoprocessor.getMessage(i));
		}

	}

}
