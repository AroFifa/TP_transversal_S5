import { IonBackButton, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonCol, IonGrid, IonItem, IonLabel, IonList, IonRow } from '@ionic/react';
import './FicheContainer.css';
interface ContainerProps {
    object?: object;
    toshow?:any[];
    info?:object;
    img?:String;


}



const FicheContainer: React.FC<ContainerProps> = (props) => {
    var info:any=props.info;
    var object:any=props.object;


function getValue(obj:any,column:string) {
    var splits=column.split(".");
  
    var value:any=obj;
    splits.map((element)=>{
      value=value[element];
      
    });
    
    return value;
  
}

var img:any=props.img?props.img:require("./card-media.png");


  return (
    <IonCard>      
      
      <img src={img} />

      <IonCardHeader>
        <IonCardTitle>{info.title}</IonCardTitle>
        <IonCardSubtitle>{info.subtitle}</IonCardSubtitle>
      </IonCardHeader>

      <IonCardContent>
        <IonList>
        {props.toshow?.map((element,index)=>{
            return (
                <IonItem key={index}>
                    <IonLabel ><strong>{element.label} :</strong></IonLabel>
                    <IonLabel>{getValue(object,element.col)}</IonLabel>
                </IonItem>
            );
        })}
        </IonList>
      </IonCardContent>
    </IonCard>

  );
};

export default FicheContainer;
