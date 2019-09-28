package administrator.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import myService.imp.UpdateIndividualUserServiceImp;
/**
 * 
 * 管理员进行成员的头像更新
 *
 */
@WebServlet("/AUpdateImageServlet")
public class AUpdateImageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String name = "";
		String imageName = "";
		boolean flag = false;
		//验证前端页面是否包含Multipart字段信息
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("isMultipart="+isMultipart);
		try
		{
			if(isMultipart)
			{
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
//				迭代器获取前端发送的信息
				Iterator<FileItem> iterator = items.iterator();
				FileItem item = null;
				while(iterator.hasNext())
				{
					item = iterator.next();
					if(item.isFormField())
					{
						if(item.getFieldName().equals("name"))
						{
//							获取用户名
							name = item.getString("utf-8");
						}
					}
					else
					{
//						获取用户选择的图片信息
						imageName = item.getName();
						int index1 = imageName.lastIndexOf("\\");
//						取出图片名
						imageName = imageName.substring(index1+1);
//						判断所选文件格式是否正确
						if(imageName.endsWith("jpg")||imageName.endsWith("png")||imageName.endsWith("jpeg")||imageName.endsWith("gif")
								||imageName.endsWith("JPG")||imageName.endsWith("JPEG"))
						{
//							上传的保存路径  采用虚拟路径映射
							String path = "/Image";
							File file = new File(path,imageName);
//							设置缓冲区大小
							factory.setSizeThreshold(1024*10);
//							缓冲区路径
							factory.setRepository(new File("/Image/tempImage"));
//							设置上传文件的大小限制
							upload.setSizeMax(1024*100);
							System.out.println("file="+file);
							item.write(file);
//							判断文件是否成功上传
							flag = true;
						}
					}
				}
				if(flag)
				{
//					上传文件成功则将文件名保存在数据库中
					boolean upload_result = new UpdateIndividualUserServiceImp().uploadUserImage(name, imageName);
					if(upload_result)
					{
						request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
					}
				}
				else
				{
//					文件不合法
					request.setAttribute("message", "图片类型错误！！");
					request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
				}
			}
		}
		catch(FileUploadBase.FileSizeLimitExceededException e)
		{
			e.printStackTrace();
			System.out.println("所选文件大小超出限制范围！！（大于1M）");
		}
		catch(FileUploadException fe)
		{
			fe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
