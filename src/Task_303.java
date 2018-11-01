import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Task_303 {
	public static void main(String[] args) {
		try(Formatter output = new Formatter("OUTPUT.txt")) {
			Numerics test = new Numerics();
			output.format("%d", test.computePlusMinus());
		}catch (FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
	}
}



//-----------------------------------------------------------------------------
/*public*/class Numerics {
	private ArrayList<Integer> numericsArray; //storage of sequence of numbers
	//-----------------------------------------------------------------------------constructors
	/*public*/private Numerics(String path)
	{
		try(Scanner input = new Scanner(Paths.get(path))) {
			this.numericsArray = new ArrayList<>();
			//-----------------------------------------------------------------------------
			if(input.hasNext()) {
				//-----------------------------------------------------------------------------
				String numericsString = input.nextLine().strip(); //read data from file
				//-----------------------------------------------------------------------------
				if(this.isCorrectParametersOfListOfSuicides(numericsString)) { //check data
					for (Character ch : numericsString.toCharArray()) {
						this.numericsArray.add(Integer.valueOf(ch.toString())); //write data to array
						//impossible Integer.valueOf(ch) ---- why?
					}
				}
				//-----------------------------------------------------------------------------
				else {
					throw new IOException("Incorrect value in file!");
				}
				//-----------------------------------------------------------------------------
			}
			//-----------------------------------------------------------------------------
			else {
				throw new IOException("File is empty!");
			}
			//-----------------------------------------------------------------------------
		}catch (IOException | NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/*public*/Numerics()
	{
		this("INPUT.txt");
	}
	//-----------------------------------------------------------------------------methods for constructors
	private boolean isCorrectParametersOfListOfSuicides(String s)
	{
		return (s.matches("[1-9]+")) && (s.length() >= 2) && (s.length() <= 50);
	}
	//-----------------------------------------------------------------------------
	//-----------------------------------------------------------------------------methods
	/*public*/int computePlusMinus()
	{
		int maxSum = 0;
		boolean isPlus = true;
		//-----------------------------------------------------------------------------
		for(int i = 0; i < this.numericsArray.size(); i++) {
			int sumTemp = 0;
			//-----------------------------------------------------------------------------
			for(int j = 0; j < this.numericsArray.size(); j++) {
				//-----------------------------------------------------------------------------
				if(j != i) {
					//-----------------------------------------------------------------------------
					if(isPlus) {
						sumTemp+= this.numericsArray.get(j);
						isPlus = false;
					}
					else {
						sumTemp-= this.numericsArray.get(j);
						isPlus = true;
					}
					//-----------------------------------------------------------------------------
				}
				//-----------------------------------------------------------------------------
			}
			//-----------------------------------------------------------------------------
			if(sumTemp > maxSum) {
				maxSum = sumTemp;
			}
			isPlus = true;
			//-----------------------------------------------------------------------------
		}
		//-----------------------------------------------------------------------------
		return maxSum;
	}
}
