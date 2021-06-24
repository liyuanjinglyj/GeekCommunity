package com.liyuanjinglyj.geekcommunity.slice;

import com.liyuanjinglyj.geekcommunity.ResourceTable;
import com.liyuanjinglyj.geekcommunity.adapter.MyPageProvider;
import com.liyuanjinglyj.geekcommunity.model.PageModel;
import com.zzrv5.mylibrary.ZZRCallBack;
import com.zzrv5.mylibrary.ZZRHttp;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.PageSlider;
import ohos.agp.components.TabList;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x00201, "TAG");
    private PageSlider pageSlider;
    private TabList tabList;
    private String[] tab_str_list = {"关注", "推荐", "热榜", "问答", "社区", "Blink"};

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        this.pageSlider = (PageSlider) findComponentById(ResourceTable.Id_ability_main_pageslider);
        this.pageSlider.setProvider(new MyPageProvider(getData(), this));
        this.tabList = (TabList) findComponentById(ResourceTable.Id_ability_main_tablist);
        for (int i = 0; i < tab_str_list.length; i++) {
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(tab_str_list[i]);
            this.tabList.addTab(tab);
            if (i == 0) {
                tab.select();
            }
        }
        this.tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                //当某个Tab从未选中状态变为选中状态时的回调
                pageSlider.setCurrentPage(tab.getPosition());
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
                //当某个Tab从选中状态变为未选中状态时的回调
            }

            @Override
            public void onReselected(TabList.Tab tab) {
                //当某个Tab已处于选中状态，再次被点击时的状态回调
            }
        });
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int itemPos, float itemPosOffset, int itemPosPixles) {

            }

            @Override
            public void onPageSlideStateChanged(int state) {
            }

            @Override
            public void onPageChosen(int itemPos) {
                tabList.selectTabAt(itemPos);
            }
        });
    }

    private List<PageModel> getData() {
        List<PageModel> pageModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pageModelList.add(new PageModel("Page" + (i + 1)));
        }
        return pageModelList;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
