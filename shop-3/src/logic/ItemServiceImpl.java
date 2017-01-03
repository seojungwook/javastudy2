package logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.ItemDao;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> getItemList() {

		return itemDao.findAll();
	}

	@Override
	public Item getItemById(Integer itemId) {

		return itemDao.getItemById(itemId);
	}

	@Override
	public List<Item> searchList(String itemName) {

		return itemDao.searchList(itemName);
	}

	@Override
	public void entryItem(Item item , HttpServletRequest request) {
		//item.getPicture() :업로드된 파일의 내용을 저장함.
		if(item.getPicture() != null && !item.getPicture().isEmpty()){
			uploadFileCreate(item.getPicture(),request);
		}
		    itemDao.create(item);
		
	}
	@Override
	public void entryUpdate(Item item , HttpServletRequest request) {
		//item.getPicture() :업로드된 파일의 내용을 저장함.
		if(item.getPicture() != null && !item.getPicture().isEmpty()){
			uploadFileCreate(item.getPicture(),request);
		
		}
			itemDao.update(item);
		
		    
		
	}
	private void uploadFileCreate(MultipartFile picture ,HttpServletRequest request) {
		// requset.getservvletContext.getRealPath(/)
		//
		String uploadPath = request.getServletContext().getRealPath("/")+"/img/";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(uploadPath + picture.getOriginalFilename());
			//picture.getInputStream():파일의 내용을 읽기위한 스트림
			InputStream in = picture.getInputStream();
			int data;
			while ((data = in.read()) != -1) {
				fos.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos !=null){
				fos.flush();
				fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(Integer itemId) {
		itemDao.delete(itemId);
		
	}

}
