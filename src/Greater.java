import java.util.Scanner;
public class Greater {


//import java.util.Scanner;


private static Scanner s;

public static void main(String[]args)
{
s = new Scanner(System.in);
int[]a=new int[3];
System.out.println("enter any three numbers");
for(int i=0;i<3;i++)
{
a[i]=s.nextInt();
}
if((a[0]>a[1])&&(a[0]>a[2]))
{
System.out.println("greater is"+a[0]);
}
else
if((a[1]>a[0])&&(a[1]>a[2]))
{
System.out.println("greater is"+a[1]);
}
else
if((a[2]>a[0])&&(a[2]>a[1]))
{
System.out.println("greater is"+a[2]);
}}
}