package utility;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DirectoryListing {

    public static String[] getListing(String path){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        Set<String> setOfFiles = new HashSet<>();

        if (listOfFiles!=null){
            for (File child : listOfFiles){
                if (child.isFile() && child.getName().endsWith(".html")){
                        System.out.println(child.getName());
                        setOfFiles.add(child.getName());
                }
            }
        }
        String[] listOfFileNames = setOfFiles.toArray(new String[setOfFiles.size()]);
        return listOfFileNames;
    }

//    public static Set<String> getSetListing(String path){
//        File folder = new File(path);
//        File[] listOfFiles = folder.listFiles();
//        Set<String> setOfFiles = new HashSet<>();
//
//        if (listOfFiles!=null){
//            for (File child : listOfFiles){
//                if (child.isFile() && child.getName().endsWith(".html")){
//                    System.out.println(child.getName());
//                    setOfFiles.add(child.getName());
//                }
//            }
//        }
//        return setOfFiles;
//    }
}
