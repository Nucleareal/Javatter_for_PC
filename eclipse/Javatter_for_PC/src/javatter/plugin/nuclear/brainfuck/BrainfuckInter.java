package javatter.plugin.nuclear.brainfuck;

public class BrainfuckInter
{
	private char[] _mem;
	private int cursor = 0;

	public String run(String param)
	{
		String res = "";
		_mem = new char[300];
		for(int i = 0; i < param.length(); i++)
		{
			switch(param.charAt(i))
			{
				case '>' : cursor++; break;
				case '<' : cursor--; break;
				case '+' :_mem[cursor]++; break;
				case '-' :_mem[cursor]--; break;
				case '.' : res += _mem[cursor]; break;
				case '[' :
				{
					if(_mem[cursor] == 0)
					{
						int bk = 0, j = 1;
						for(; param.charAt(i+j)!=']' || bk!=0; j++)
						{
							if(param.charAt(i+j) == '[') bk++;
							if(param.charAt(i+j) == ']') bk--;
						}
						i += j;
					}
					break;
				}
				case ']' :
				{
					if(_mem[cursor] != 0)
					{
						int bk = 0, j = i-1;
						for(; param.charAt(j) != '[' || bk!=0; j--)
						{
							if(param.charAt(j) == ']') bk++;
							if(param.charAt(j) == '[') bk--;
						}
						i = j;
					}
					break;
				}
			}
		}
		return res;
	}

	private static BrainfuckInter _ins = new BrainfuckInter();
	private BrainfuckInter(){}
	public static BrainfuckInter get()
	{
		return _ins;
	}
}
