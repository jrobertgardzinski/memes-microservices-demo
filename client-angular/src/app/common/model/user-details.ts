export interface Authority {
    authority: string;
}

export interface User {
    password?: any;
    username: string;
    authorities: Authority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    enabled: boolean;
}

export interface UserDetails {
    user: User;
    authorities: string[];
}