package com.amazonaws.lambda.demo;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, Object> {

	@Override
	public Object handleRequest(Map<String, Object> input, Context arg1) {

		Map<String, Object> currentIntent = (Map<String, Object>) input.get("currentIntent");
		System.out.println("currentIntent >> " +currentIntent);
		String slotString = currentIntent.get("slots").toString();
		System.out.println("slotString >> " +slotString);
		LexRequest lexRequest = (LexRequest) LexRequestFactory.createLexRequest(input);
		DialogAction dialogAction = new DialogAction("Close", "Fulfilled", new Message("PlainText",
				"Sorry I did not understand. Please visit site XXX for other detail."));
		ResponseCard responseCard;

		try {
			
			//street
			int equalIndex7 = slotString.indexOf("emailaddress=") + 13;
			String responseToLexMsg7 = slotString.substring(equalIndex7);
			int commaIndex7 = responseToLexMsg7.indexOf("}");
			String emailaddress = responseToLexMsg7.substring(0, commaIndex7);
			System.out.println("emailaddress >> "+emailaddress);
			if (!(emailaddress.equalsIgnoreCase("null"))) {
				if(isValidEmailAddress(emailaddress)) {
					Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
					dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "emailaddress",
							new Message("PlainText", "You will receive an email in few seconds. Please reply by attaching address proof. Once reviewed, necessary actions will be initiated, we will confirm you once your address is updated in system."));
				}else {
					Message message = new Message("PlainText",
							"This is not a valid emailaddress. Please visit site XXX for other detail.");
						dialogAction = new DialogAction("Close", "Fulfilled", message);
				}
			}else {
				//street
				int equalIndex6 = slotString.indexOf("street=") + 7;
				String responseToLexMsg6 = slotString.substring(equalIndex6);
				int commaIndex6 = responseToLexMsg6.indexOf(",");
				String street = responseToLexMsg6.substring(0, commaIndex6);
				System.out.println("street >> "+street);
				if (!(street.equalsIgnoreCase("null"))) {
					Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
						dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "emailaddress",
								new Message("PlainText", "Please provide your email address."));
				}else {
					//pincode
					int equalIndex5 = slotString.indexOf("pincode=") + 8;
					String responseToLexMsg5 = slotString.substring(equalIndex5);
					int commaIndex5 = responseToLexMsg5.indexOf(",");
					String pincode = responseToLexMsg5.substring(0, commaIndex5);
					System.out.println("pincode >> "+pincode);
					if (!(pincode.equalsIgnoreCase("null"))) {
						if (isValidZip(pincode)) {
						Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
							dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "street",
									new Message("PlainText", "Please provide your street address, avoid using comma;"));
						} else {
							Message message = new Message("PlainText",
								"This is not a valid pincode. Please visit site XXX for other detail.");
							dialogAction = new DialogAction("Close", "Fulfilled", message);
						}
					}else {
						//usa
						int equalIndex4 = slotString.indexOf("usa=") + 4;
						String responseToLexMsg4 = slotString.substring(equalIndex4);
						int commaIndex4 = responseToLexMsg4.indexOf(",");
						String fromUSA = responseToLexMsg4.substring(0, commaIndex4);
						System.out.println("fromUSA >> "+fromUSA);
						if (!(fromUSA.equalsIgnoreCase("null"))) {
							if (fromUSA.indexOf("n") == -1) {
							Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
								dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "pincode",
										new Message("PlainText", "Please provide your pincode"));
							} else {
								Message message = new Message("PlainText",
									"This service is to help only USA customer to change address. Please visit site XXX for other detail.");
								dialogAction = new DialogAction("Close", "Fulfilled", message);
							}
						}else {
							//addressChangeIntent
							int equalIndex3 = slotString.indexOf("address=") + 8;
							String responseToLexMsg3 = slotString.substring(equalIndex3);
							int commaIndex3 = responseToLexMsg3.indexOf(",");
							String addressChangeIntent = responseToLexMsg3.substring(0, commaIndex3);
							System.out.println("addressChangeIntent >> "+addressChangeIntent);
							if (!(addressChangeIntent.equalsIgnoreCase("null"))) {
								if (addressChangeIntent.equalsIgnoreCase("address")) {
									Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
									dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "usa",
											new Message("PlainText", "Are you from USA?"));
								} else {
									Message message = new Message("PlainText",
											"This service is to help existing customer to change address. Please visit site XXX for other detail.");
									dialogAction = new DialogAction("Close", "Fulfilled", message);
								}
							}else {
								// PolicyNumber
								int equalIndex2 = slotString.indexOf("policynumber=") + 13;
								String responseToLexMsg2 = slotString.substring(equalIndex2);
								int commaIndex2 = responseToLexMsg2.indexOf(",");
								String policyNumber = responseToLexMsg2.substring(0, commaIndex2);
								System.out.println("policyNumber >> "+policyNumber);
								if (!(policyNumber.equalsIgnoreCase("null"))) {
									if (isNumber(policyNumber)) {
										//SERVICE CALL
										Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
										dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "address",
												new Message("PlainText", "Hi Leena, how can I help you today?"));
									} else {
										Message message = new Message("PlainText",
												"This is not a valid policy number. Please try again.");
										Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
										dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "policynumber", message);
									}
								}else {
									// CustomerType
									int equalIndex1 = slotString.indexOf("customertype=") + 13;
									String responseToLexMsg1 = slotString.substring(equalIndex1);
									int commaIndex1 = responseToLexMsg1.indexOf(",");
									String customerType = responseToLexMsg1.substring(0, commaIndex1);
									System.out.println("customerType >> "+customerType);
									if (!(customerType.equalsIgnoreCase("null"))) {
										if (customerType.equalsIgnoreCase("existing")) {
											Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null", "null");
											dialogAction = new DialogAction("ElicitSlot", "AskAddressChange", slots, "policynumber",
													new Message("PlainText", "Please provide your policy number."));
										} else {
											Message message = new Message("PlainText",
													"This service is to help existing customer to change address. Please visit site XXX for other detail.");
											dialogAction = new DialogAction("Close", "Fulfilled", message);
										}
									}

								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			dialogAction = new DialogAction("Close", "Fulfilled", new Message("PlainText",
					"Sorry , I did not understand. Please try again from beginning." + e.getMessage()));
			return new LexResponse(dialogAction);
		}

		return new LexResponse(dialogAction);
	}
	
	public static boolean isNumber(String s) {
		  boolean amIValid = false;
		  try {
		   Integer.parseInt(s);
		   // s is a valid integer!
		   amIValid = true;
		   return amIValid;
		  } catch (NumberFormatException e) {
			  amIValid = false;
			  return amIValid;
		  }
		  
	}
	
	public static boolean isValidZip(String zip) {
		String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zip);
	    return matcher.matches();
	}
	
	
	public static boolean isValidEmailAddress(String email) {
		return EmailValidator.getInstance().isValid(email);
	}


}
