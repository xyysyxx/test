/**
 * Created by XYY on 2017/3/26.
 */
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
public class caluate {
    public static void main(String args[]) {
        Random rand = new Random();//设置随机数
        DecimalFormat df =new DecimalFormat("0.00");//设置小数点后位数规格
        int n[]=new int [20];//整数
        int n1[]=new int [20]; //分数
        float sum[]=new float[10]; //整数的计算结果
        float sum1[]=new float[10]; // 分数分子的计算结果
        float min[]=new float[10];  // 最小公倍数（作分母结果）
        float max[]=new float[10];//最大公约数
        int k=0;int rate=0;
        float answer1[]=new float [8];
        float answer2[][]=new float[4][2];
        Scanner an=new Scanner(System.in);
        for(int i=0;i<16;i++)  //输入随机数入数组
        {
            n[i] = rand.nextInt(100)+1;
            n1[i]=rand.nextInt(10)+1;
        }
        while(k!=8)//最小公倍数
        {
            for(int i=0;i<8;i=i+2) {
                min[k] = minCommonMultiple(n1[i], n1[i + 1]);
                k++;
            }
        }

       k=0;


        while(k!=8)//最大公约数
        {
            for(int i=0;i<8;i=i+2) {
                max[k] = maxCommonDivisor(n1[i], n1[i + 1]);
                k++;
            }
        }

        for(int i=0;i<8;i++) //整数四则运算算式输出
        {
            if (i == 0) {
                System.out.println(n[i] + "+" + n[i + 1] + "=");
                sum[i] =(float) n[i]+ n[i + 1];
            }//0
            if (i == 2) {
                System.out.println(n[i] + "-" + n[i + 1] + "=");
                sum[i - 1] =(float) n[i] - n[i + 1];
            } //1
            if (i == 4) {
                System.out.println(n[i] + "*" + n[i + 1] + "=");
                sum[i - 2] =(float) n[i] * n[i + 1];
            }  //2
            if (i == 6) {
                System.out.println(n[i] + "÷" + n[i + 1] + "=");
                sum[i - 3] =(float) n[i] / n[i + 1];
            } //3

        }
        for(int i=0;i<16;i=i+4)//输出分数运算式
        {
            if(i==0)
            {
                System.out.println(n1[i]+"/"+n1[i+1]+"+"+n1[i+2]+"/"+n1[i+3]+"=");
                sum1[i]=min[i]/n1[i+1]*n1[i]+min[i]/n1[i+3]*n1[i+2];
            }//0
            if(i==4)
            {
                System.out.println(n1[i]+"/"+n1[i+1]+"-"+n1[i+2]+"/"+n1[i+3]+"=");
                sum1[i-3]=(min[i-3]/n1[i+1]*n1[i])-(min[i-3]/n1[i+3]*n1[i+2]);
            }//1
            if(i==8)
            {
                System.out.println(n1[i]+"/"+n1[i+1]+"*"+n1[i+2]+"/"+n1[i+3]+"=");
                sum1[i-6]=(min[i-6]/n1[i+1]*n1[i])*(min[i-6]/n1[i+3]*n1[i+2]);
            }//2
            if(i==12)
            {
                System.out.println(n1[i]+"/"+n1[i+1]+"÷"+n1[i+2]+"/"+n1[i+3]+"=");
                sum1[i-9]=(min[i-9]/n1[i+1]*n1[i])/(min[i-9]/n1[i+3]*n1[i+2]);
            }//3
        }
        System.out.println("请输入答案，用空格分隔，分号请用空格代替");
        for(int i=0;i<4;i++)
        {
            answer1[i]= (float) an.nextDouble();
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<2;j++)
                answer2[i][j]=(float)an.nextDouble();
        }
        for(int i=0;i<4;i++)
        {
            if(answer1[i]==sum[i])
            rate++;
        }
        for(int i=0;i<4;i++)
        {

            if(answer2[i][1]==sum1[i]/max[i]&&answer2[i][2]==min[i]/max[i])
            rate++;
        }
        System.out.println("答案：");
        for(int j=0;j<4;j++)
        {
           System.out.print(df.format(sum[j])+" ");
       }
        System.out.println(" ");
        for(int j=0;j<4;j++)
        {
            System.out.print(df.format(sum1[j]/max[j])+"/"+min[j]/max[j]+" ");
        }
        System.out.println(" ");
        System.out.println("正确数目："+rate+"正确率："+rate/8*100+"%");

    }
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return maxCommonDivisor(n, m % n);
        }
    }
    public static int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }


}
