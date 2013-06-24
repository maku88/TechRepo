package pl.maku.tech.PcapAnalizer;

import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{

    private static final String path = "C:\\Dropbox\\Dropbox\\mobi-id\\SWUS\\analiza";
    private static final String zestaw = "\\zestaw3";

    public static void main( String[] args ) throws IOException {

        List<Packet> inputPackets =  new ArrayList<Packet>();

        List<Packet> outputPackets = new ArrayList<Packet>();

        Map<String, Integer> inputTimeMap = new TreeMap<String, Integer>();
        Map<String, Integer> outputTimeMap= new TreeMap<String, Integer>();

        inputPackets.addAll(getPackets(path+zestaw+"\\tr-3-0.txt"));
        inputPackets.addAll(getPackets(path+zestaw+"\\tr-3-1.txt"));
        inputPackets.addAll(getPackets(path+zestaw+"\\tr-3-2.txt"));
        outputPackets.addAll(getPackets(path+zestaw+"\\tr-3-3.txt"));

        inputTimeMap = getTimeMap(inputPackets);
        outputTimeMap = getTimeMap(outputPackets);

        for(String s : inputTimeMap.keySet()) {
                System.out.println(s + " : " + inputTimeMap.get(s));
        }

        for(String s : outputTimeMap.keySet()) {
            System.out.println(s + " " + outputTimeMap.get(s));
        }

        writeFile(inputTimeMap,"wchodzace");
        writeFile(outputTimeMap,"wychodzace");

        LineChart chart = new LineChart(path+zestaw+"\\java");

        chart.createLineChart(inputTimeMap,"pakiety wchodzace");
        chart.createLineChart(outputTimeMap,"pakiety wychodzace");
    }

    private static Map<String, Integer>  getTimeMap(List<Packet> inputPackets) {
        Map<String, Integer> timeMap = new TreeMap<String, Integer>();
        int count = 0;
        for(Packet p :inputPackets ) {
            System.out.println(count + " " + p.toString());

            count++;

            String timeTrim = p.getTime().substring(0,5);

            if(timeMap.containsKey(timeTrim)) {
                int i = timeMap.get(timeTrim);
                i++;
                timeMap.put(timeTrim, i);
            }else {
                timeMap.put(timeTrim, 1);
            }
        }

        return timeMap;
    }


    private static void writeFile(Map<String, Integer> mapToWrite, String fileName) throws IOException {
        // Create file
        FileWriter fstream = new FileWriter(path+zestaw+"\\java\\"+fileName + ".txt");
        BufferedWriter out = new BufferedWriter(fstream);

        for(String s : mapToWrite.keySet()) {
            String line = s + " " + mapToWrite.get(s);
            out.write(line + "\n");
        }
        //Close the output stream
        out.close();
    }



    private static List<Packet> getPackets(String filePath) throws IOException {
        List<Packet> retList = new ArrayList<Packet>();

        FileInputStream fstream = new FileInputStream(filePath);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            String[] lineArray = strLine.split(" ");
            retList.add(new Packet(lineArray[0],Integer.parseInt(lineArray[7].replace(",","")),lineArray[21],lineArray[23]));
        }
        in.close();

        return  retList;
    }



}
