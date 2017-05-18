/*
 * Created by XYY on 2017/5/1.
 */
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;
public class caluate {
    public static void main(String args[]) {
        Random rand = new Random();//设置随机数
        DecimalFormat df =new DecimalFormat("0.0");//设置小数点后位数规格
        int n[]=new int [20];//整数
        int Numerator[]=new int [10];//分子
        int Denominator[]=new int [10]; //分母
        double sum[]=new double[10]; //整数的计算结果
        int Numsum[]=new int[10]; // 分数分子的计算结果
        int Densum[]=new int[10]; // 分数分母的计算结果
        int max1[]=new int[10];//算式最大公约数，算式分数化简用
        int max2[]=new int[10];//结果最大公约数，分数运算化简用
        int k=0;double rate=0;//正确率
        String answer1[][]=new String[10][10];//存放分数答案
        String mix_equation[][]= new String [3][3];//存放混合运算算式
        String mixsum[]=new String[3]; //混合运算的计算结果
        String[] sign={"+","-","×","÷","÷"};//存放四种运算符号
        Scanner an=new Scanner(System.in);
        String result_user =""; String mind="";

        for(int i=0;i<16;i++)  //输入随机数入整数数组
        n[i] = rand.nextInt(100)+1;
        for(int i=0;i<8;i++)  //输入整数入分母数组
            Denominator[i]=rand.nextInt(100)+1;
        for(int i=0;i<8;i++)  //输入整数入分子数组，且小于相应的分母大小
            Numerator[i]=rand.nextInt(Denominator[i])+1;

        for(int i=0;i<2;i++)//生成前两个混合算式
        {
            for(int j=0;j<3;j++) {
            k=rand.nextInt(50)+1;
            rate=rand.nextInt(4);
            mind=mind+k+" "+sign[(int)rate]+" ";
            }
            k=rand.nextInt(50)+1;
            mix_equation[i][1]=mind+k;
            mind="";
        }
        int p=0;
        switch (p)//生成带括号的混合算式
        {
            case 0:
            {
                k=rand.nextInt(50)+1;
                rate=rand.nextInt(1);
                mind="("+" "+k+" "+sign[(int)rate]+" "+(k/2)+" "+")";
                p++;
            }
            case 1:
            {
                k=rand.nextInt(50)+1;
                rate=2+rand.nextInt(3);
                mind=mind+" "+sign[(int)rate]+" "+k;
            }

        }
        mix_equation[2][1]=mind;

       k=0;rate=0;

       while(k!=8)//计算算式最大公约数
        {
            for(int i=0;i<8;i++) {
                max1[k] = maxCommonDivisor(Numerator[i], Denominator[i]);
                k++;
            }
        }

        System.out.println("请运算以下题目:");
        for(int i=0;i<8;i++) //整数四则运算算式输出
        {
            if (i == 0) {
                System.out.println(n[i] + " + " + n[i + 1] + " =");
                sum[i] = n[i]+ n[i + 1];
            }//0
            if (i == 2) {
                System.out.println(n[i] + " - " + n[i + 1] + " =");
                sum[i - 1] = n[i] - n[i + 1];
            } //1
            if (i == 4) {
                System.out.println(n[i] + " × " + n[i + 1] + " =");
                sum[i - 2] = n[i] * n[i + 1];
            }  //2
            if (i == 6) {
                System.out.println(n[i] + " ÷ " + n[i + 1] + " =");
                sum[i - 3] =n[i] / n[i + 1]*0.1 ;
            } //3

        }
        for(int i=0;i<8;i=i+2)//输出分数运算式
        {
            if(i==0)
            {
                System.out.println(Numerator[i]/max1[i]+"/"+Denominator[i]/max1[i]+" + "+Numerator[i+1]/max1[i+1]+"/"+Denominator[i+1]/max1[i+1]+" =");
                Numsum[i]=Numerator[i]*Denominator[i+1]+Numerator[i+1]*Denominator[i];
                Densum[i]=Denominator[i]*Denominator[i+1];
                max2[i] = maxCommonDivisor(Numsum[i], Densum[i]);//计算结果最大公约数
            }//0 +
            if(i==2)
            {
                System.out.println(Numerator[i]/max1[i]+"/"+Denominator[i]/max1[i]+" - "+Numerator[i+1]/max1[i+1]+"/"+Denominator[i+1]/max1[i+1]+" =");
                Numsum[i]=Numerator[i]*Denominator[i+1]-Numerator[i+1]*Denominator[i];
                Densum[i]=Denominator[i]*Denominator[i+1];
                max2[i] = maxCommonDivisor(Numsum[i], Densum[i]);//计算结果最大公约数
            }//1 -
            if(i==4)
            {
                System.out.println(Numerator[i]/max1[i]+"/"+Denominator[i]/max1[i]+" × "+Numerator[i+1]/max1[i+1]+"/"+Denominator[i+1]/max1[i+1]+" =");
                Numsum[i]=Numerator[i]*Numerator[i+1];
                Densum[i]=Denominator[i]*Denominator[i+1];
                max2[i] = maxCommonDivisor(Numsum[i], Densum[i]);//计算结果最大公约数
            }//2 x
            if(i==6)
            {
                System.out.println(Numerator[i]/max1[i]+"/"+Denominator[i]/max1[i]+" ÷ "+Numerator[i+1]/max1[i+1]+"/"+Denominator[i+1]/max1[i+1]+" =");
                Numsum[i]=Numerator[i]*Denominator[i+1];
                Densum[i]=Denominator[i]*Numerator[i+1];
                max2[i] = maxCommonDivisor(Numsum[i], Densum[i]);//计算结果最大公约数
            }//3 /
        }
        for(int i=0;i<3;i++)//输出混合运算表达式
        {
            System.out.println(mix_equation[i][1]+" =");
        }
        //输入分数运算答案
        for(int i=0;i<8;i=i+2) {
            for (int j = 0; j <3; j++) {
                if(Numsum[i]==0)//如果分子为0则答案为0
                    answer1[i][j]=0+"";
                if(Densum[i]==1)//如果分母为1则答案等同分子
                    answer1[i][j]=Numsum[i]+"";
                else//都不是则按照 x/x 的格式输入字符串数组
                answer1[i][j] = (Numsum[i] / max2[i]) + "/" + (Densum[i] / max2[i]) + "";
            }
        }
        //输入混合运算答案
        for(int i=0;i<3;i++)
        {
            mixsum[i]=mixed_mode(mix_equation[i][1]);
        }

        System.out.println("请输入整数运算答案（保留一位小数）:");
         //输入答案
        //判断答案格式
         //比较答案
        for(int i=0;i<4;i++) //输入整数答案，判断整数答案格式，输入数据与答案进行比较
        {
            result_user=an.next();
            judge_form_Z(result_user);
           if( String.valueOf(sum[i]).equals(result_user))
               rate++;
        }
        System.out.println("请输入分数运算答案:");
        for(int i=0;i<8;i=i+2) //输入分数答案，判断分数答案格式，输入数据与答案进行比较
        {
            result_user=an.next();
            judge_form_F(result_user);
            for (int j = 0; j <3; j++) {
                if( answer1[i][j].equals(result_user))
                    rate++;
            }

        }
        System.out.println("请输入混合运算答案（取整）:");
        for(int i=0;i<3;i++) //输入整数答案，判断整数答案格式，输入数据与答案进行比较
        {
            result_user=an.next();
            judge_form_Z(result_user);
            if( String.valueOf(mixsum[i]).equals(result_user))
                rate++;
        }



        System.out.println("答案：");
        for(int j=0;j<4;j++)
        {
           System.out.print(df.format(sum[j])+" ");
       }
        System.out.println(" ");
        for(int i=0;i<8;i=i+2) {
            for (int j = 0; j <1; j++) {
                    System.out.print(answer1[i][j]);
            }
            System.out.print(" ");
        }
        System.out.println(" ");
        for(int i=0;i<3;i++)
        {
            System.out.print(mixsum[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
        //输出答案
        System.out.println("正确数目："+(int)rate+"正确率："+(rate/8*100)+"%");//输出正确率

    }

    public static int maxCommonDivisor(int m, int n) {
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }
    public static void judge_form_F(String a)//判断输入字符串的格式_分数答案（正则标识符）
    {
        String p="";
        Scanner answer =new Scanner(System.in);
        Pattern pattern =Pattern.compile("^-?[0-9]*[/][1-9]*");//输入字符串格式为 数字/数字
        Matcher matcher = pattern.matcher(a);
        boolean rs =matcher.find();//判断
        if(!rs)
        {
            System.out.println("输入错误，请重新输入：");
            a=answer.next();
            judge_form_F(a);
        }
    }
    public static void judge_form_Z(String a)//判断输入字符串的格式_整数答案（正则标识符）
    {
        String p="";
        Scanner answer =new Scanner(System.in);
        Pattern pattern =Pattern.compile("^-?[0-9]+([.][0-9]+){0,1}$");//输入字符串格式为 整数或小数
       // Pattern pattern1 =Pattern.compile("^-?[0,9]*/.[0,9]*");//输入字符串格式为 数字.数字
        Matcher matcher = pattern.matcher(a);
       // Matcher matcher1 = pattern1.matcher(a);
        boolean rs = matcher.find();//判断
       // boolean rs1 = matcher1.find();
        if(!rs)
        {

            System.out.println("输入错误，请重新输入：");
            a=answer.next();
            judge_form_Z(a);
        }
    }

    public static String mixed_mode(String x)
    {
        String a="";
        testlast back =new testlast();
        a=back.main(x);
        x="";
        return a;
    }







}
