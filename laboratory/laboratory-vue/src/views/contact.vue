<template>
  <div id="amapcontainer" style="width: 800px; height: 620px;margin: 0 auto;" />
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
window._AMapSecurityConfig = {
  securityJsCode: '9fd9e4fa44b8b80ccfb3b96349b3bf82' // '「申请的安全密钥」',
}
export default {

  name: "MapComponent",
  data() {
    return {
      adress: '淄博市张店区新村西路266号', // 根据位置查找经纬度
      map: null,
      auto: null,
      geocoders: null,
      markers: [], // 标记点 数组
      lnglat: [], // 当前位置的经纬度
      mapForm: {
        resizeEnable: true,
        viewMode: '3D', // 使用3D视图
        zoomEnable: true, // 地图是否可缩放，默认值为true
        dragEnable: true, // 地图是否可通过鼠标拖拽平移，默认为true
        doubleClickZoom: true, // 地图是否可通过双击鼠标放大地图，默认为true
        zoom: 13, // 初始化地图级别
        center: [117.065237, 36.680017], // 初始化中心点坐标 山东济南
        // mapStyle: "amap://styles/darkblue", // 设置颜色底层
        placeSearch: null // 搜索关键字
      },
      amapLoaded: false, // 标识高德地图是否加载完成
    };
  },
  created() {
    this.initAMap()
  },
  mounted() {
  },
  methods: {
    initAMap() {
      console.log('初始化地图')
      AMapLoader.load({
        key: '745b10144f63c797b3ec077f8956c96c', // 申请好的Web端开发者Key，首次调用 load 时必填
        version: '2.0', // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.Scale', 'AMap.ToolBar', 'AMap.ControlBar', 'AMap.Geocoder', 'AMap.Marker',
          'AMap.CitySearch', 'AMap.Geolocation', 'AMap.AutoComplete', 'AMap.InfoWindow', 'AMap.PlaceSearch'] // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap) => {
        // 获取到作为地图容器的DOM元素，创建地图实例
        this.map = new AMap.Map('amapcontainer', // 设置地图容器id
          this.mapForm
        )
        this.geoCode(this.adress)
      }).catch(e => {
        console.log(e)
      })
    },
    async geoCode(arr) {
      const _this = this
      console.log('要查询的地点', _this.adress)
      _this.geocoders = await new AMap.Geocoder({
        city: '' // 默认：“全国”
      })
      _this.geocoders.getLocation(_this.adress, function (status, result) {
        if (status === 'complete' && result.geocodes.length) {
          const local = result.geocodes[0].location
          const arr = [local.lng, local.lat]
          _this.mapForm.center = arr
          // 初始化地图
          _this.initAMap()
        } else {
          console.log('根据地址查询位置失败')
        }
      })
    },
  }
};
</script>

<style scoped>
#map-container {
  width: 100%;
  height: 400px;
}
</style>
