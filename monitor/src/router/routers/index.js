import Layout from '@/components/layout'
import {routes} from "../routes.js"

function findChild(data){
    let children = [];
    if (!data){
        return children;
    }
    for (let i = 0,leng = data.length; i < leng; i++) {
        if (!data[i].component || data[i].children && data[i].children.length > 0){
            children.push(...findChild(data[i].children));
        }else {
            children.push(data[i]);
        }
    }
    return children;
}
let rootRoute = null;
const notChildren = [];
const child = [];
routes && routes.map(item => {
    if (item.path != "/"){
        if (item.children && item.children.length > 0){
            //item.children.map(router => child.push(router));
            child.push(...findChild(item.children));
        }else{
            notChildren.push(item);
        }
    }else {
        rootRoute = item;
    }
    return item;
});
let index = -1;
let defaultRouter = [];
notChildren.length>0 && routes.some(router => {
    index = notChildren.findIndex(item => item.path == router.path);
    return index != -1;
}) && (index != -1) && (defaultRouter=notChildren.splice(index,4));

const children = {
    path:rootRoute.path,
    name:rootRoute.name,
    component: Layout,
    redirect:rootRoute.redirect,
    isMenu:rootRoute.isMenu!==false,
    children:[rootRoute.children[0],...child,...notChildren]
};
const startRouter = [...defaultRouter,children];
export default startRouter;