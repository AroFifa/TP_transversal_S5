import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import Liste_avions from './Liste_avions';
import './Tab1.css';

const Tab1: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Liste des avions</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Liste des avions</IonTitle>
          </IonToolbar>
        </IonHeader>
        <Liste_avions></Liste_avions>
      </IonContent>
    </IonPage>
  );
};

export default Tab1;
