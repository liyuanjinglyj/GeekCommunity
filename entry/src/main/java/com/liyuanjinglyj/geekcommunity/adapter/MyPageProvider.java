package com.liyuanjinglyj.geekcommunity.adapter;

import com.liyuanjinglyj.geekcommunity.ResourceTable;
import com.liyuanjinglyj.geekcommunity.model.PageModel;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class MyPageProvider extends PageSliderProvider {
    private AbilitySlice abilitySlice;
    private List<PageModel> list;

    public MyPageProvider(List<PageModel> list,AbilitySlice abilitySlice){
        this.list=list;
        this.abilitySlice=abilitySlice;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        final Component cpt;
        cpt = LayoutScatter.getInstance(this.abilitySlice).parse(ResourceTable.Layout_pageslider_layout, null, false);
        componentContainer.addComponent(cpt);
        cpt.setLayoutConfig(new StackLayout.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_PARENT,
                ComponentContainer.LayoutConfig.MATCH_PARENT
        ));
        PageModel pageModel = this.list.get(i);
        Text name = (Text) cpt.findComponentById(ResourceTable.Id_pageslider_layout_name);
        name.setText(pageModel.getName());
        return cpt;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent((Component) o);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return true;
    }
}
