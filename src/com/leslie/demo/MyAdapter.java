package com.leslie.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
	private String[] list;
	private Context context;
	private AsyncImageLoader imageLoader;

	public MyAdapter(Context context, String[] list) {
		this.context = context;
		this.list = list;
		imageLoader = new AsyncImageLoader(context);
	}

	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			holder.img = (ImageView) convertView.findViewById(R.id.userimage);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final String imgUrl = list[position];
		// 给 ImageView 设置一个 tag
		holder.img.setTag(imgUrl);
		// 预设一个图片
		holder.img.setImageResource(R.drawable.ic_launcher);

		if (!TextUtils.isEmpty(imgUrl)) {
			Bitmap bitmap = imageLoader.loadImage(holder.img, imgUrl);
			if (bitmap != null) {
				holder.img.setImageBitmap(bitmap);
			}
		}

		return convertView;
	}

	class ViewHolder {
		ImageView img;
	}
}
