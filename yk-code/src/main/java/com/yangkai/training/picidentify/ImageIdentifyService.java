
/**
 * @author yangkai
 *
 */
package com.training.picidentify;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

public class ImageIdentifyService {

    private IdentifyType type;

    private IdentifyProvider provider;




	/**
	 * @param type
	 *            传入的数据类型，IdentifyType.image为本地文件, IdentifyType.url 为图片 Url
	 * @param url
	 *            文件集合(本地文件路径或者 Url)
	 * @return
	 */
	public ImageIdentifier imageBuilder() {
		return new ImageIdentifier();
	}

	public UrlIdentifier urlBuilder() {
		return new UrlIdentifier();
	}

    static class ImageIdentifier {
		private List<File> images = Lists.newArrayList();

		public ImageIdentifier add(File image){
			this.images.add(image);
			return this;
		}

		public ImageIdentifier addAll(List<File> images) {
			this.images.addAll(images);
			return this;
		}
		
		public void identify() {
			
		}
	}

	static class UrlIdentifier {
		private List<String> urls = Lists.newArrayList();
		public UrlIdentifier add(String url){
			this.urls.add(url);
			return this;
		}

		public UrlIdentifier addAll(List<String> urls) {
			this.urls.addAll(urls);
			return this;
		}

		public ImageIdentifier build() {
		    return new TupuImageIdentifier();
        }

        public ImageIdentifier build(IdentifyProvider provider) {
            return new TupuImageIdentifier();
        }

	}

	//图谱
	static class TupuImageIdentifier implements ImageIdentifier {



    }

    //腾讯云(万象优图)
    static class QcloudImageIdentifier implements ImageIdentifier {

    }
}