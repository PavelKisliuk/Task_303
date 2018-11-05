import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Task_303 {
	public static void main(String[] args) {
		Numerics test = new Numerics();
		try(Formatter output = new Formatter("OUTPUT.TXT")) {
			output.format(test.toString());
		}catch (FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------------------------------------------------
	/*public*/ static class Numerics {
		//-----------------------------------------------------------------------------fields
		private ArrayList<Integer> numericsArray; //storage of sequence of numbers
		//-----------------------------------------------------------------------------constructors
		/*public*/private Numerics(String path)
		{
			this.numericsArray = new ArrayList<>();
			try(Scanner input = new Scanner(Paths.get(path))) {
				//-----------------------------------------------------------------------------
				if(input.hasNext()) {
					//-----------------------------------------------------------------------------
					String numericsString = input.nextLine(); //read data from file
					//-----------------------------------------------------------------------------
					if(this.isCorrectParametersOfNumericsString(numericsString)) {
						for (Character ch : numericsString.toCharArray()) {
							this.numericsArray.add(Character.getNumericValue(ch));
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
			this("INPUT.TXT");
		}
		//-----------------------------------------------------------------------------methods for constructors
		private boolean isCorrectParametersOfNumericsString(String s)
		{
			return (s.matches("[1-9]\\d+")) && (s.length() >= 2) && (s.length() <= 50);
		}
		//-----------------------------------------------------------------------------
		//-----------------------------------------------------------------------------methods
		/*public*/private Integer computePlusMinus()
		{
			ArrayList<Integer> integerArrayList = new ArrayList<>();
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
				integerArrayList.add(sumTemp);
				isPlus = true;
				//-----------------------------------------------------------------------------
			}
			//-----------------------------------------------------------------------------
			return Collections.max(integerArrayList);
		}

		@Override
		public String toString()
		{
			return String.valueOf(this.computePlusMinus());
		}
	}
}