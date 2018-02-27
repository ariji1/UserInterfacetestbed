package userInterfaceTestbed;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface testbed to develop and test UI ideas.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2018 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2018-02-03 The JavaFX-based GUI for the implementation of a testbed
 * 
 */

@SuppressWarnings("restriction")
public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;

	// These are the application values required by the user interface
	private Label label_ApplicationTitle = new Label("UI Testbed");
	private Label label_Operand1 = new Label("First operand");
	private Label label_unit = new Label("Select unit");
	private TextField text_Operand1MeasuredValue = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField();
//	private TextField text_unit = new TextField();
	private Button button_Go= new Button("Go");
	private Label label_errOperand1MeasuredValue = new Label("");	
	private Label label_errOperand1ErrorTerm = new Label("");	
	private TextFlow errMeasuredValue;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
	private TextFlow errErrorTerm;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();
    boolean boolUpdatingComboBox = true;
    int selectListState = 0;
    String stringDefaultItem = "";
    String stringSelectItem1 = "";
	int intFoundIndex1 = 1;
	String stringSelectItem2 = "";
	int intFoundIndex2 = 2;
	String stringSelectItem3 = "";
	int intFoundIndex3 = 1;
//    private SelectList unit_list = new SelectList();
    ObservableList<String> emptyList = FXCollections.observableArrayList();
    ObservableList<String> arrayCountries = FXCollections.observableArrayList("- no country selected - ",
			"Abkhazia", "Afghanistan", "Albania", "Algeria", "Andorra",
			"Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
			"Austria", "Azerbaijan", "Bahamas, The", "Bahrain", "Bangladesh",
			"Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil",
			"Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi",
			"Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic",
			"Chad", "Chile", "China", "China (Taiwan), Republic of ", "Colombia",
			"Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Cook Islands", "Costa Rica",
			"Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
			"Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
			"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Estonia", "Ethiopia", "Fiji", "France", "Gabon",
			"Gambia, The", "Georgia", "Germany", "Ghana", "Greece",
			"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Honduras", "Hungary", "Iceland", "India",
			"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
			"Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South",
			"Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
			"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
			"Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi",
			"Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
			"Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova",
			"Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
			"Myanmar (Burma)", "Nagorno-Karabakh ", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
			"Niue", "Northern Cyprus ", "Norway", "Oman", "Pakistan",
			"Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Poland", "Portugal", "Qatar",
			"Romania", "Russia", "Rwanda", "Sahrawi Arab Democratic Republic", "Saint Kitts and Nevis",
			"Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "São Tomé and Príncipe",
			"Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"Somaliland", "South Ossetia", "South Sudan", "Spain", "Sri Lanka",
			"Sudan", "Sudan, South", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand",
			"Timor-Leste", "Togo", "Tonga", "Transnistria", "Trinidad and Tobago",
			"Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda",
			"Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
			"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
			"Yemen", "Zambia", "Zimbabwe");
    ComboBox<String> comboboxSelectunit;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_ApplicationTitle, "Arial", 24, UserInterfaceTestbed.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, UserInterfaceTestbed.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		
		//
		setupLabelUI(label_unit, "Arial", 18, UserInterfaceTestbed.WINDOW_WIDTH-60,Pos.BASELINE_RIGHT , -35, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1 measured value,
		// measured value process all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1MeasuredValue, "Arial", 18, UserInterfaceTestbed.WINDOW_WIDTH/2 
				+ 30, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1MeasuredValue.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand1MeasuredValue(); });
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 150, Pos.BASELINE_LEFT, 
				UserInterfaceTestbed.WINDOW_WIDTH/2 + 50, 70, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand1ErrorTerm(); });
		
		//
		
		comboboxSelectunit = new ComboBox<String>();
		comboboxSelectunit.setStyle("-fx-font: 14 \"Arial\";");
		comboboxSelectunit.setOnAction((event) -> {if (!boolUpdatingComboBox) selectCountryItem(); });
		comboboxSelectunit.setPrefWidth(150);
		comboboxSelectunit.setMinHeight(30);
		comboboxSelectunit.setLayoutX(618);
		comboboxSelectunit.setLayoutY(70);
		
		loadComboBoxData();
		comboboxSelectunit.getSelectionModel().select(0);
		boolUpdatingComboBox = false;

		
		
//		setupTextUI(text_unit, "Arial", 18, 150, Pos.BASELINE_RIGHT, 
//				UserInterfaceTestbed.WINDOW_WIDTH/2 + 218, 70, false);
		
		// Establish an error message for the first operand Measured Value, left aligned
		label_errOperand1MeasuredValue.setTextFill(Color.RED);
		label_errOperand1MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1MeasuredValue, "Arial", 14,  
						UserInterfaceTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);
		
		// Establish an error message for the first operand Error Term, left aligned
		label_errOperand1ErrorTerm.setTextFill(Color.RED);
		label_errOperand1ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1ErrorTerm, "Arial", 14, 
						UserInterfaceTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 
						UserInterfaceTestbed.WINDOW_WIDTH/2 - 150, 126);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Go, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, // changed from size from 32 to 48
						UserInterfaceTestbed.WINDOW_WIDTH/2-BUTTON_WIDTH/2, 300);
		button_Go.setOnAction((event) -> { performGo(); });
		button_Go.setTextFill(Color.RED);// assigning red color to the button label
		
		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue.setMinWidth(UserInterfaceTestbed.WINDOW_WIDTH-10); 
		errMeasuredValue.setLayoutX(22);  
		errMeasuredValue.setLayoutY(100);
		
		// Error Message for the Error Term for operand 1
	    errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm = new TextFlow(errETPart1, errETPart2);
		// Establish an error message for the second operand just above it with, left aligned
		errErrorTerm.setMinWidth(UserInterfaceTestbed.WINDOW_WIDTH-10); 
		errErrorTerm.setLayoutX(UserInterfaceTestbed.WINDOW_WIDTH/2+63);  
		errErrorTerm.setLayoutY(100);

		
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_ApplicationTitle, label_Operand1, text_Operand1MeasuredValue,label_unit, 
				text_Operand1ErrorTerm, label_errOperand1MeasuredValue, label_errOperand1ErrorTerm, 
				button_Go, errMeasuredValue, errErrorTerm,comboboxSelectunit);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	private void selectCountryItem() {
    	// There are two cases depending on where a previously selected list items have been selected
    	String theSelectedItem = (String) comboboxSelectunit.getSelectionModel().getSelectedItem();
    	int theSelectedIndex = comboboxSelectunit.getSelectionModel().getSelectedIndex();
    	
    	// This switch uses the display state to determine how to do the work
    	switch (selectListState) {
    	
    	// This display state say that there was a previously selected item
    	case 1:
    		
    		// The following code determines which part of the select list has been selected
    		switch (theSelectedIndex) {
    		
    		// The previously used element was selected
    		case 0:
//    			outputSelectedCountry.setText(stringSelectItem1);
//    			outputSelectedCountryIndex.setText(intFoundIndex1 + "");
       	    	boolUpdatingComboBox = true;
       	    	comboboxSelectunit.getSelectionModel().select(0);
        	    boolUpdatingComboBox = false;
    			break;
    			
    		// The dashed line or the - no country selected - item was selected
    		case 1:
    		case 2:
//    			outputSelectedCountry.setText("- no country selected - ");
//    			outputSelectedCountryIndex.setText("0");
    			selectListState = 0;
    			stringSelectItem1 = "";
    			intFoundIndex1 = 0;
    			Platform.runLater(new Runnable() {
    			    @Override public void run() {
    			    loadComboBoxData();
    			}});
    			break;
    			
    		// The items below the - no country selected - item was selected
    		default:
//    			outputSelectedCountry.setText(theSelectedItem);
//    			outputSelectedCountryIndex.setText((theSelectedIndex - 2) + "");
     			stringSelectItem1 = theSelectedItem;
    			intFoundIndex1 = theSelectedIndex - 2;
    			selectListState = 2;
    			Platform.runLater(new Runnable() {
    			    @Override public void run() {
    			    loadComboBoxData();
    			}});
    			break;
    		}
    		break;
    	case 2:
			switch(theSelectedIndex) {
			
			case 0:
//				outputSelectedCountry.setText(stringSelectItem1);
//				outputSelectedCountryIndex.setText(intFoundIndex1 + "");
				boolUpdatingComboBox = true;
				comboboxSelectunit.getSelectionModel().select(0);
				boolUpdatingComboBox = false;
				break;
			case 1:
//				outputSelectedCountry.setText(stringSelectItem2);
//    			outputSelectedCountryIndex.setText(intFoundIndex2 + "");
				String stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			int intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
    			break;
			case 2:
			case 3:
//				outputSelectedCountry.setText("- no country selected - ");
//				outputSelectedCountryIndex.setText("0");
				selectListState = 0;
				stringSelectItem1 = "";
				intFoundIndex1 = 0;
				stringSelectItem2 = "";
				intFoundIndex2 = 0;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			default:
				//outputSelectedCountry.setText(theSelectedItem);
//				outputSelectedCountryIndex.setText((theSelectedIndex - 3) + "");
				stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 3;
    			selectListState = 2;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			}
			break;
				
		

    			
        // This display state say that there was no previously selected item
    	default:
    		if( theSelectedIndex == 0) {
    			// This is the code for the - no country selected - items was selected
//    			outputSelectedCountry.setText(theSelectedItem);
    			comboboxSelectunit.getSelectionModel().select(0);
    		} else {
    			// This is the code for an item below the - no country selected - items was selected
//    			outputSelectedCountry.setText(theSelectedItem);
//    			outputSelectedCountryIndex.setText(theSelectedIndex + "");
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex1 = theSelectedIndex;
    			selectListState = 1;
    			// to update UI elements from non-main thread
    			Platform.runLater(new Runnable() {
    			    @Override public void run() {
    			    loadComboBoxData();
    			}});
   			break;
    		}
    	}
    }
	
	private void loadComboBoxData() {
    	// We are going to change the ComboBox, so we need to turn off
    	// event processing
    	boolUpdatingComboBox = true;


    	// Define the ComboBox select list

    	// We start by clearing the old list so it is empty
    	comboboxSelectunit.getItems().clear();


    	// Based on the state of the select list, display the right number
    	// of previously selected items on the top of the whole list
    	switch (selectListState) {
    	// This is the case where a previous item was selected
    	case 1:
    		comboboxSelectunit.getItems().addAll(stringSelectItem1,"----------");
    		stringDefaultItem = stringSelectItem1;
    		comboboxSelectunit.getItems().addAll(arrayCountries);
    		break;
    	case 2:
			comboboxSelectunit.getItems().addAll(stringSelectItem1,stringSelectItem2,"----------");
			stringDefaultItem = stringSelectItem1;
			comboboxSelectunit.getItems().addAll(arrayCountries);
			break;
    		
    	// This is the case where no previous item was selected
    	default:
    		stringDefaultItem = "";
    		comboboxSelectunit.getItems().addAll(arrayCountries);
    		break;
    	}

    	comboboxSelectunit.getSelectionModel().select(0);
		 Platform.runLater(new Runnable() {
			 @Override public void run() {
				 ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) comboboxSelectunit.getSkin();
			     ((ListView<?>) skin.getPopupContent()).scrollTo(0);
				 }});

    	// Done updating the ComboBox, so we can now process any events
    	boolUpdatingComboBox=false;
    }
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	private void setOperand1MeasuredValue() {
		label_errOperand1MeasuredValue.setText("");
		errMVPart1.setText("");
		errMVPart2.setText("");
		performGo();
	}
	
	private void setOperand1ErrorTerm() {
		label_errOperand1ErrorTerm.setText("");
		errETPart1.setText("");
		errETPart2.setText("");
		performGo();
		}
	
	private void performGo() {
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand1MeasuredValue.getText());
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand1MeasuredValue.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart1.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}
		else { 
			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
				String input = ErrorTermRecognizer.errorTermInput;
				if (ErrorTermRecognizer.errorTermIndexofError <= -1) return;
				errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
				errETPart2.setText("\u21EB");
			}
			
		}
	}
}
