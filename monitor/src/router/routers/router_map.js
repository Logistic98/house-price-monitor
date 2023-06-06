import {routesMap} from "../routes.js"

const handleRoutesMap = [];
routesMap && routesMap.map(item => {
    if (item.children && item.children.length > 0){
        item.children.map(child => handleRoutesMap.push(child));
    }else{
        handleRoutesMap.push(item);
    }
    return item;
});
export {handleRoutesMap,routesMap};