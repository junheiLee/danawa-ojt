import React from 'react';
import {Background} from './Styles';
import Spinner from '../fix/spinner.gif';

export const Loading = () => {
    return (
        <Background>
            <img src={Spinner} alt="로딩중" width="5%"/>
        </Background>
    )
};

export default Loading;