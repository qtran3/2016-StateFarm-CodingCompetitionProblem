package com.statefarm.codingcomp.agent;

import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.statefarm.codingcomp.utilities.SFFileReader;

public class MAINCONSOLE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SFFileReader sfFileReader=new SFFileReader();
		Document doc = Jsoup.parse(sfFileReader.readFile(Paths.get("src", "test", "resources", "KevinParks.html").toString()));
		Elements lists=doc.select("span[itemprop=\"name\"]");
		System.out.println(lists.get(0).text());
		
		Elements productLists=doc.select("div[itemprop=description]").get(0).select("ul").get(0).select("li");;
		System.out.println(productLists.get(0).text());
	}

}
