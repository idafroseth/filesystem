package com.svenskeknappen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	@RequestMapping(value = {"/home","/index","/"})
	public String index(ModelMap model, Principal principal) {
		model.addAttribute("account", principal);
		return "home";
	}

	@RequestMapping(value = "/filesystem")
	public String listFilesAndFolders(ModelMap model,  Principal principal) {
		model.addAttribute("account", principal);

		FileSystem fileSys = fileSystemService.getCurrentSystem("");
		if (fileSys != null) {
			model.addAttribute("files", fileSys.getFiles());
			model.addAttribute("folders", fileSys.getFolders());
		}

		model.addAttribute("basepath_word", "QP");
		model.addAttribute("basepath", "/");

		return "filesystem";
	}

	@RequestMapping(value = "/filesystem/folder/{basepath_word}/{folder}")
	public String listFilesAndFolders(ModelMap model, @PathVariable("basepath_word") String directoryName,
			@PathVariable("folder") String folder,  Principal principal) {
		model.addAttribute("account", principal);
		if (directoryName != null) {
			if (directoryName.contains("QP")) {
				directoryName = directoryName.replace("QP", "/");
			}
			if (!directoryName.endsWith("/")) {
				directoryName = directoryName + "/";
			}

		} else {
			directoryName = "/";
		}
		if (folder != null) {
			directoryName += folder;
		}

		FileSystem fileSys = fileSystemService.getCurrentSystem(directoryName);
		if (fileSys != null) {
			model.addAttribute("files", fileSys.getFiles());
			model.addAttribute("folders", fileSys.getFolders());
		}

		model.addAttribute("basepath", directoryName);
		model.addAttribute("basepath_word", directoryName.replace("/", "QP"));
		return "filesystem";
	}

	@RequestMapping(value = "/filesystem/{basepath_word}")
	public String listParentFolder(ModelMap model, @PathVariable("basepath_word") String directoryName, Principal principal) {
		model.addAttribute("account", principal);

		directoryName = getDirectoryName(directoryName);
		directoryName = getParentDirectory(directoryName);

		System.out.println("Trying to fetch content of: " + directoryName);
		FileSystem fileSys = fileSystemService.getCurrentSystem(directoryName);
		if (fileSys != null) {
			model.addAttribute("files", fileSys.getFiles());
			model.addAttribute("folders", fileSys.getFolders());
		}

		model.addAttribute("basepath", directoryName);
		model.addAttribute("basepath_word", directoryName.replace("/", "QP"));
		return "filesystem";
	}

	@RequestMapping(value = "/file/{basepath_word}/{file}")
	public String showFile(ModelMap model, @PathVariable("basepath_word") String directoryName, @PathVariable("file") String filename, Principal principal) {
		model.addAttribute("account", principal);
		model.addAttribute("filename", filename);
		directoryName = getDirectoryName(directoryName);
		
		return "file";
	}
	
	
	private String getParentDirectory(String directory) {
		if (directory == null) {
			return "/";
		}
		String dir = "";
		String[] folder = directory.split("/");
		for (int i = 0; i < folder.length - 1; i++) {
			dir += folder[i]+"/";
		}
		return dir;
	}

	
	private String getDirectoryName(String directoryName){
		if (directoryName != null) {
			if (directoryName.contains("QP")) {
				System.out.println("Directory contains QP:" + directoryName);
				directoryName = directoryName.replace("QP", "/");
			}

		} else {
			directoryName = "/";
		}
		return directoryName;
	}
	
	@RequestMapping(value = "/users")
	public String getAllUsers() {
		repositoryService.getAllUsers();
		return "home";
	}
}
