package javatter.plugin.nuclear;

import com.orekyuu.javatter.util.SaveData;

public enum SettingType
{
	CheckBox{
		@Override
		public IViewCreator getCreator(SaveData data)
		{
			return new Builder_CheckBox(data);
		}
	};

	public IViewCreator getCreator(SaveData data)
	{
		return NullBuilder.getInstance();
	}
}
