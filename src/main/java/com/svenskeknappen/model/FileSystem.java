package com.svenskeknappen.model;

import java.util.List;

public class FileSystem {

	public FileSystem(List<String> folders, List<String> files){
		this.folders = folders;
		this.files = files;
	}
	
	List<String> files;
	List<String> folders;
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	public List<String> getFolders() {
		return folders;
	}
	public void setFolders(List<String> folders) {
		this.folders = folders;
	}
	
	
}
