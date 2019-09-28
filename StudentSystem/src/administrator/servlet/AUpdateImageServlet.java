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
 * ����Ա���г�Ա��ͷ�����
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
		//��֤ǰ��ҳ���Ƿ����Multipart�ֶ���Ϣ
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("isMultipart="+isMultipart);
		try
		{
			if(isMultipart)
			{
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
//				��������ȡǰ�˷��͵���Ϣ
				Iterator<FileItem> iterator = items.iterator();
				FileItem item = null;
				while(iterator.hasNext())
				{
					item = iterator.next();
					if(item.isFormField())
					{
						if(item.getFieldName().equals("name"))
						{
//							��ȡ�û���
							name = item.getString("utf-8");
						}
					}
					else
					{
//						��ȡ�û�ѡ���ͼƬ��Ϣ
						imageName = item.getName();
						int index1 = imageName.lastIndexOf("\\");
//						ȡ��ͼƬ��
						imageName = imageName.substring(index1+1);
//						�ж���ѡ�ļ���ʽ�Ƿ���ȷ
						if(imageName.endsWith("jpg")||imageName.endsWith("png")||imageName.endsWith("jpeg")||imageName.endsWith("gif")
								||imageName.endsWith("JPG")||imageName.endsWith("JPEG"))
						{
//							�ϴ��ı���·��  ��������·��ӳ��
							String path = "/Image";
							File file = new File(path,imageName);
//							���û�������С
							factory.setSizeThreshold(1024*10);
//							������·��
							factory.setRepository(new File("/Image/tempImage"));
//							�����ϴ��ļ��Ĵ�С����
							upload.setSizeMax(1024*100);
							System.out.println("file="+file);
							item.write(file);
//							�ж��ļ��Ƿ�ɹ��ϴ�
							flag = true;
						}
					}
				}
				if(flag)
				{
//					�ϴ��ļ��ɹ����ļ������������ݿ���
					boolean upload_result = new UpdateIndividualUserServiceImp().uploadUserImage(name, imageName);
					if(upload_result)
					{
						request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
					}
				}
				else
				{
//					�ļ����Ϸ�
					request.setAttribute("message", "ͼƬ���ʹ��󣡣�");
					request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
				}
			}
		}
		catch(FileUploadBase.FileSizeLimitExceededException e)
		{
			e.printStackTrace();
			System.out.println("��ѡ�ļ���С�������Ʒ�Χ����������1M��");
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
