package com.svenskeknappen.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.svenskeknappen.model.FileSystem;

@Service("filesystemService")
public class FileSystemImpl implements FileSystemService {

	@Override
	public FileSystem getCurrentSystem(String directoryName) {
		 File directory = new File(System.getProperty("user.dir"));
	    	if(directoryName != null){
	    		directory = new File(directoryName);
	    	}
	       
	        //get all the files from a directory
	        File[] fileList = directory.listFiles();
	        ArrayList<String> folders = new ArrayList<String>();
	        ArrayList<String> files = new ArrayList<String>();
	        
	        for (File file : fileList){
	        	if(file.isDirectory()){
	        		folders.add(file.getName());
	        	}
	        	else{
	        		 files.add(file.getName());
	        	}  
	        }
	        return new FileSystem(folders, files);
	}

}
