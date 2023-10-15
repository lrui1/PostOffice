// vue
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// e1-plus
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  esbuild:{//打包时去除console和debugger代码
    drop:["console","debugger"]
  },
  plugins: [
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
    vue()
  ],
  server: {
    port: 7070
  }
})
