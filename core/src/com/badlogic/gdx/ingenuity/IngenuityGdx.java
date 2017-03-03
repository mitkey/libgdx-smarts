package com.badlogic.gdx.ingenuity;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.ingenuity.screen.HallScreen;
import com.badlogic.gdx.ingenuity.screen.LoadingScreen;
import com.badlogic.gdx.ingenuity.screen.LoadingScreen.AssetsCategory;
import com.badlogic.gdx.ingenuity.screen.LoadingScreen.ILoadingComplete;
import com.badlogic.gdx.ingenuity.screen.LoginScreen;
import com.badlogic.gdx.ingenuity.screen.RoomScreen;
import com.badlogic.gdx.ingenuity.utils.FnAssetManager;
import com.badlogic.gdx.ingenuity.utils.LazyBitmapFont;
import com.badlogic.gdx.ingenuity.utils.helper.PixmapHelper;
import com.badlogic.gdx.ingenuity.utils.helper.RHelper;
import com.badlogic.gdx.ingenuity.utils.scene2d.SimpleScreen;

public class IngenuityGdx extends Game {

	private FreeTypeFontGenerator fontGenerator;
	private FnAssetManager assetManager;
	private LoadingScreen loadingScreen;

	private SimpleScreen chessScreen;

	@Override
	public void create() {
		LazyBitmapFont.setGlobalGenerator(fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf")));
		assetManager = new FnAssetManager();
		loadingScreen = new LoadingScreen();

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			RHelper.generated();
		}

		// 默认启动欢迎界面
		loading2Welcome();
	}

	@Override
	public void dispose() {
		super.dispose();
		if (fontGenerator != null) {
			fontGenerator.dispose();
			fontGenerator = null;
		}
		if (assetManager != null) {
			assetManager.dispose();
			assetManager = null;
		}
		if (loadingScreen != null) {
			loadingScreen.dispose();
			loadingScreen = null;
		}
		if (chessScreen != null) {
			chessScreen.dispose();
			chessScreen = null;
		}
		PixmapHelper.getInstance().dispose();
	}

	public void loading2Welcome() {
		// 切换到加载场景
		setScreen(loadingScreen);
		loadingScreen.loadAssets(AssetsCategory.common, new ILoadingComplete() {
			@Override
			public void complete() {
				// 加载完资源后，切换到登录场景
				updateScreen(new LoginScreen());
			}
		});
	}

	public void loading2Login() {
		// 切换到加载场景
		setScreen(loadingScreen);
		loadingScreen.loadAssets(AssetsCategory.login, new ILoadingComplete() {
			@Override
			public void complete() {
				// 加载完资源后，切换到登录场景
				updateScreen(new LoginScreen());
			}
		});
	}

	public void loading2Hall() {
		// 切换到加载场景
		setScreen(loadingScreen);
		loadingScreen.loadAssets(AssetsCategory.hall, new ILoadingComplete() {
			@Override
			public void complete() {
				// 加载完资源后，切换到大厅场景
				updateScreen(new HallScreen());
			}
		});
	}

	public void loading2Room() {
		// 切换到加载场景
		setScreen(loadingScreen);
		loadingScreen.loadAssets(AssetsCategory.room, new ILoadingComplete() {
			@Override
			public void complete() {
				// 加载完资源后，切换到房间场景
				updateScreen(new RoomScreen());
			}
		});
	}

	public FnAssetManager getAssetManager() {
		return assetManager;
	}

	void updateScreen(SimpleScreen simpleScreen) {
		if (chessScreen != null) {
			chessScreen.dispose();
			chessScreen = null;
		}
		chessScreen = simpleScreen;
		setScreen(chessScreen);
	}

}
