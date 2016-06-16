package com.svenskeknappen.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.svenskeknappen.model.FileSystem;

@Service("filesystemService")
public class FileSystemImpl implements FileSystemService {

	@Override
	public FileSystem getCurrentSystem(String directoryName) {
		System.out.println("");
		 File directory = new File(System.getProperty("user.dir")+directoryName);

	       
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
//	public static void main(String[] args){
//		FileSystemImpl fi = new FileSystemImpl();
//		System.out.println(fi.getCurrentSystem(""));
//		System.out.println(fi.getCurrentSystem("/"));
//		System.out.println(fi.getCurrentSystem("/src"));
//	}

	@Override
	public File getFile(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
