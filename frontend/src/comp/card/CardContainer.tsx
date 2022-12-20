import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonCol, IonGrid, IonInput, IonItem, IonRow } from '@ionic/react';
import './CardContainer.css';
import FicheContainer from '../fiche/FicheContainer';
class additional_col{
  label!: string;
  column!: string;
  link!: string;
  params!: string[];

  
  
}
class header{
  label?: string;
  col?: string;
}
interface ContainerProps {
  header?: header[];
  data?: object[];
  obj?: object;
  additional_column?: additional_col[];
}

const CardContainer: React.FC<ContainerProps> = (props) => {
  var header:any=props.header;
  return (
      <IonGrid>


        {props.data?.map((element,index)=>{
            var info={title: ""};
            var obj:any=element;
          return (
          <IonCol key={index} size='300px'>
                <FicheContainer info={info} img={obj.image} object={element} toshow={props.header} />
          </IonCol>);
        
          
        })}


      </IonGrid>

  );
};

export default CardContainer;
