package bitc.fullstack405.xml_json_parser.service;

import bitc.fullstack405.xml_json_parser.dto.kobis.DailyBoxOfficeDTO;
import bitc.fullstack405.xml_json_parser.dto.pharmacy.FullDataItemDTO;
import jakarta.xml.bind.JAXBException;

import java.util.List;

public interface ParserService {

    List<FullDataItemDTO> getItemListFile(String fileName) throws JAXBException;

    List<FullDataItemDTO> getItemListUrl(String serviceUrl) throws Exception;

    List<DailyBoxOfficeDTO> getDailyBoxOfficeList(String serviceUrl) throws Exception;
}
