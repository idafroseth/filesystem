package com.svenskeknappen.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.svenskeknappen.service.FileSystemService;
import com.svenskeknappen.service.RepositoryService;
import com.svenskeknappen.model.*;

@Controller
public class MainController {

	@Autowired
	RepositoryService repositoryService;
	
	@Autowired 
	FileSystemService fileSystemService;
    /**
     * List all the files and folders from a directory
     * @param directoryName to be listed
     */
	@RequestMapping(value="/listfiles")
    public String listFilesAndFolders(ModelMap model, String directoryName){
    	FileSystem fileSys = fileSystemService.getCurrentSystem(directoryName);
        if(fileSys != null){
        	model.addAttribute("files", fileSys.getFiles());
        	model.addAttribute("folders",fileSys.getFolders());
        }
    	return "filesystem";
    }
	
	@RequestMapping(value="/users")
    public String getAllUsers(){
    	repositoryService.getAllUsers();
        return "home";
    }
}
