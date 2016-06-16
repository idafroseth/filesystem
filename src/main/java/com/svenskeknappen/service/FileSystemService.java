package com.svenskeknappen.service;

import java.io.File;

import com.svenskeknappen.model.FileSystem;

public interface FileSystemService {
	public FileSystem getCurrentSystem(String directory);
	
	public File getFile(String path);
}
