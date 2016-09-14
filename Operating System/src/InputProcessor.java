
public class InputProcessor {


  public static InputProcessor inputprocessor = new InputProcessor();
  
  private InputProcessor(){}
   
  public static InputProcessor getInstance()
  {
	  return inputprocessor;
  }
  
  public void process(String input)
  {
    	switch(input.toUpperCase())
    	{
    	case "PROC":
    		//TODO
    		break;
    	case "MEM":
    		//TODO
    		break;
    	case "LOAD":
    		//TODO
    		break;
    	case "EXE":
    		//TODO
    		break;
    	case "RESET":
            Prompt.textArea.setText(null);
    		break;
    	case "EXIT":
    		System.exit(0);
    		break;
    	case "HELP":
    		Prompt.textArea.append("The List of availible commands are: proc, mem, load, exe, reset, and exit" + "\n");
    		break;
    	default:
            Prompt.textArea.append("Error: Not a valid input" + "\n");
    	}
  }
 
}
